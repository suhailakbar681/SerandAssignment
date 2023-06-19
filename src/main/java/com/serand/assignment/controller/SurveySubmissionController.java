package com.serand.assignment.controller;

import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.SurveySubmission;
import com.serand.assignment.service.SurveySubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/surveys/submission")
public class SurveySubmissionController {

    @Autowired
    private SurveySubmissionService surveySubmissionService;

    @GetMapping("create/dummy")
    public RestResponse addThreeDummySubmission(){
        return surveySubmissionService.addDummySubmission();
    }

    @PostMapping("/save")
    public RestResponse createSurvey(@Validated @RequestBody SurveySubmission submission) {
        return surveySubmissionService.saveSubmission(submission);
    }

    @GetMapping("/search/{score}")
    public RestResponse findSubmissionByScore(@Validated @PathVariable double score){
        return surveySubmissionService.findSubmissionByScore(score);
    }

    @GetMapping("/search/job")
    public RestResponse findSubmissionByJob(@Validated @RequestParam String jobTitle){
        return surveySubmissionService.findSubmissionByJob(jobTitle);
    }

}
