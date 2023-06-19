package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Candidate;
import com.serand.assignment.model.Job;
import com.serand.assignment.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     *
     * @param job
     * @return
     */
    public RestResponse save(Job job){
        try {
            return RestResponse.of(jobRepository.save(job), ApplicationMessages.CREATED_JOB_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_CREATED_JOB_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_CREATED_JOB_RECORD);
        }
    }

    public RestResponse deleteJobById(String id) {
        try{
            jobRepository.deleteById(id);
            return RestResponse.of(ApplicationMessages.SUCCESS_DELETE_JOB_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_DELETE_JOB_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_DELETE_JOB_RECORD);
        }
    }

    public RestResponse getJobById(String jobId) {
        try{
            Optional<Job> job = jobRepository.findById(jobId);
            if(job.isPresent()){
                return RestResponse.of(job.get());
            }
            return RestResponse.fail(ApplicationMessages.NOT_FOUND_JOB_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_FETCHING_JOB_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_FETCHING_JOB_RECORD);
        }
    }

    public RestResponse updateJob(Job updatedJob) {
        try{
            Optional<Job> existingJob = jobRepository.findById(updatedJob.getId());
            if(existingJob.isPresent()){
                Job job = existingJob.get();
                job.setTitle(updatedJob.getTitle());
                return RestResponse.of(updatedJob,ApplicationMessages.SUCCESS_UPDATE_JOB_RECORD);
            }
            else{
                return RestResponse.fail(ApplicationMessages.NOT_FOUND_JOB_RECORD);
            }
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_UPDATE_JOB_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_UPDATE_JOB_RECORD);
        }
    }

    public RestResponse getAllJobs() {
        try{
            return RestResponse.of(jobRepository.findAll());
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_FETCHING_JOB_RECORD+ex.getMessage());
            return RestResponse.of(ApplicationMessages.ERROR_FETCHING_JOB_RECORD);
        }
    }
}
