package com.serand.assignment.service;

import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.SurveySubmission;

public interface ScoringService {
    RestResponse calculateScoreBySubmissionId(SurveySubmission submission);
}
