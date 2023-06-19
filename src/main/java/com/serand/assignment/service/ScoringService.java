package com.serand.assignment.service;

import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Score;
import com.serand.assignment.model.SurveySubmission;

import java.util.List;

public interface ScoringService {
    RestResponse calculateScoreBySubmissionId(SurveySubmission submission);
    List<Score> findSubmissionByScore(double score);
}
