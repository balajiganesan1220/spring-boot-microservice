package com.hamish.job;


import com.hamish.job.DTO.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs(){
        List<JobDTO> jobs=jobService.getAllJobs();
        if(jobs.isEmpty())
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else 
            return new ResponseEntity<>(jobs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> jobById(@PathVariable Long id){
        Job job=jobService.jobById(id);
        if(job!=null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        if (createdJob != null) {
            return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job){
        Job updatedJob=jobService.updateJob(id,job);
        if(updatedJob!=null&&id!=null)
            return new ResponseEntity<>("Job Successfully Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid job or id",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Boolean isDelete=jobService.deleteJob(id);
        if(isDelete)
            return new ResponseEntity<>("Job Successfully Deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Job Not Found",HttpStatus.NOT_FOUND);
    }


}
