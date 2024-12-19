package com.hamish.job;

import com.hamish.job.DTO.JobDTO;
import com.hamish.job.clients.CompanyClient;
import com.hamish.job.clients.ReviewClient;
import com.hamish.job.external.Company;
import com.hamish.job.external.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;
    public JobServiceImpl(JobRepository jobRepository,CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "fallbackMethod")
//    @Retry(name = "companyBreaker", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "fallbackMethod")
    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs= jobRepository.findAll();
        Map<Long,Company> companyCache= new HashMap<>();
        Map<Long,List<Review>> reviewsCache=new HashMap<>();
        return jobs.stream().map(job->covertToDTO(job,companyCache,reviewsCache)).collect(Collectors.toList());
    }

    public List<String> fallbackMethod(Exception e){
        List<String> list=new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO covertToDTO(Job job, Map<Long,Company> companyCache, Map<Long,List<Review>> reviewCache){
        JobDTO jobDTO=new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        Company company=companyCache.computeIfAbsent(job.getCompanyId(), companyClient::getCompany);
        jobDTO.setCompany(company);
        List<Review> reviews=reviewCache.computeIfAbsent(job.getCompanyId(), reviewClient::getReviews);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }


    @Override
    public Job jobById(Long id) {
        Optional<Job> job=jobRepository.findById(id);
        return job.orElse(null);
    }

    @Override
    public Job createJob(Job job) {
        if(job!=null)
        {
            return jobRepository.save(job);
        }
        return null;

    }

    @Override
    public Job updateJob(Long id, Job job) {
        if(id==null || job==null){
            return null;

        }
        Optional<Job> optional=jobRepository.findById(id);
        if(optional.isPresent()) {
            Job updatedJob = optional.get();
            updatedJob.setLocation(job.getLocation());
            updatedJob.setDescription(job.getDescription());
            updatedJob.setTitle(job.getTitle());
            updatedJob.setMinSalary(job.getMinSalary());
            updatedJob.setMaxSalary(job.getMaxSalary());
            return jobRepository.save(updatedJob);
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteJob(Long id) {

        if(id==null||!jobRepository.existsById(id))
          return false;
        jobRepository.deleteById(id);
        return true;
    }


}
