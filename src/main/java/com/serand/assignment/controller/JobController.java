package com.serand.assignment.controller;

import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Job;
import com.serand.assignment.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/save")
    public RestResponse createJob(@Validated @RequestBody Job job) {
        return jobService.save(job);
    }

    @DeleteMapping("/delete/{id}")
    public RestResponse deleteJob(@Validated @PathVariable("id") String id) {
        return jobService.deleteJobById(id);
    }

    @GetMapping("/get/id/{jobId}")
    public RestResponse getJobById(@Validated @PathVariable("jobId") String jobId) {
        return jobService.getJobById(jobId);
    }

    @PutMapping("/update")
    public RestResponse updateJob(@Validated @RequestBody Job job){
        return jobService.updateJob(job);
    }

    @GetMapping("/all")
    public RestResponse findAll(){
        return jobService.getAllJobs();
    }
}
