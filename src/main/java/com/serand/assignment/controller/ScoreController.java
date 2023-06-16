package com.serand.assignment.controller;

import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.SurveySubmission;
import com.serand.assignment.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/score")
public class ScoreController {
    @Autowired
    private ScoringService scoringService;

    @GetMapping("/calculate")
    public RestResponse addThreeDummySubmission(@Validated @RequestBody SurveySubmission submission){
        return scoringService.calculateScoreBySubmissionId(submission);
    }
}
