package com.hamish.job;

import com.hamish.job.DTO.JobDTO;

import java.util.List;

public interface JobService {
    public List<JobDTO> getAllJobs();
    public Job jobById(Long id);
    Job createJob(Job job);

    Job updateJob(Long id, Job job);

    Boolean deleteJob(Long id);
}
