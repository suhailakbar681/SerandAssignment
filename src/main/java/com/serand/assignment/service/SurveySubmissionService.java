package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.common.dto.response.SubmissionsByScore;
import com.serand.assignment.model.*;
import com.serand.assignment.repository.SurveySubmissionRepository;
import com.serand.assignment.utils.DummySurveyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class SurveySubmissionService{

    @Autowired
    private SurveySubmissionRepository surveySubmissionRepository;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ScoringService scoringService;

    /**
     *
     * @param surveySubmission
     * @return
     */
    public SurveySubmission save(SurveySubmission surveySubmission){
        try{
            return surveySubmissionRepository.save(surveySubmission);
        }
        catch (Exception ex){
            log.error("Error in saving Survey Submission: "+ex.getMessage());
            return null;
        }
    }

    public RestResponse saveSubmission(SurveySubmission submission){
        SurveySubmission submission1 = save(submission);
        if(submission1 != null){
            scoringService.calculateScoreBySubmissionId(submission1);
            return RestResponse.of(submission1,ApplicationMessages.CREATED_SUBMISSION_RECORD);
        }
        return RestResponse.fail(ApplicationMessages.ERROR_CREATED_SUBMISSION_RECORD);
    }

    public RestResponse findSubmissionByScore(double score) {
        if(score >= 0){
            List<Score> scoreList = scoringService.findSubmissionByScore(score);
            List<SubmissionsByScore> submissionList = new ArrayList<>();

            scoreList.forEach(score1 -> {
                SubmissionsByScore submissionsByScore = new SubmissionsByScore();
                SurveySubmission submission = findSubmissionById(score1.getSurveySubmission().getId());
                submissionsByScore.setScore(score1.getScore());
                submissionsByScore.setSubmissionId(submission.getId());
                submissionsByScore.setCandidateId(submission.getCandidate().getId());
                submissionsByScore.setSurveyId(submission.getSurvey().getId());
                submissionList.add(submissionsByScore);
            });
            return RestResponse.of(submissionList);
        }
        return RestResponse.fail(ApplicationMessages.ERROR_SUBMISSION_SCORE_VALUE);
    }
    public SurveySubmission findSubmissionById(String submissionId){
        try{
            Optional<SurveySubmission> surveySubmission = surveySubmissionRepository.findById(submissionId);
            if(surveySubmission.isPresent()){
                return surveySubmission.get();
            }
            else{
                log.error("No Survey submission found by submissionId");
                return null;
            }
        }
        catch (Exception ex){
            log.error("Error in finding survey submission by submissionId: "+ex.getMessage());
            return null;
        }
    }

    /**
     *
     * @return
     */
    public RestResponse addDummySubmission(){

        try{

            Survey dummySurvey =  DummySurveyData.getDummySurvey(surveyService, companyService, jobService);

            Map<String, String> surveyResponse1 = new HashMap<>();
            surveyResponse1.put("question1", "Black");
            surveyResponse1.put("question2", "PHP");
            surveyResponse1.put("question3", "Intellij");
            surveyResponse1.put("question4", "1,2");

            Map<String, String> surveyResponse2 = new HashMap<>();
            surveyResponse2.put("question1", "Green");
            surveyResponse2.put("question2", "HTML");
            surveyResponse2.put("question3", "VS Code");
            surveyResponse2.put("question4", "1,3");

            Map<String, String> surveyResponse3 = new HashMap<>();
            surveyResponse3.put("question1", "Blue");
            surveyResponse3.put("question2", "REACT");
            surveyResponse3.put("question3", "Eclipse");
            surveyResponse3.put("question4", "1,4");

            SurveySubmission submission = new SurveySubmission();
            submission.setSurvey(dummySurvey);
            submission.setCandidate(DummySurveyData.getNewDummyCandidate(candidateService));
            submission.setResponses(surveyResponse1);
            SurveySubmission submission1 =  save(submission);
            if(submission1 != null){
                scoringService.calculateScoreBySubmissionId(submission1);
            }

            SurveySubmission submission2 = new SurveySubmission();
            submission2.setSurvey(dummySurvey);
            submission2.setCandidate(DummySurveyData.getNewDummyCandidate(candidateService));
            submission2.setResponses(surveyResponse2);
            SurveySubmission submission22 =  save(submission2);
            if(submission22 != null){
                scoringService.calculateScoreBySubmissionId(submission22);
            }

            SurveySubmission submission3 = new SurveySubmission();
            submission3.setSurvey(dummySurvey);
            submission3.setCandidate(DummySurveyData.getNewDummyCandidate(candidateService));
            submission3.setResponses(surveyResponse3);
            SurveySubmission submission33 =  save(submission3);
            if(submission33 != null){
                scoringService.calculateScoreBySubmissionId(submission33);
            }

            return RestResponse.success("Dummy submission added");
        }
        catch (Exception ex){
            log.error("Error in Survey submission: "+ex.getMessage());
            return RestResponse.success("Error in Survey submission: "+ex.getMessage());
        }
    }

    /**
     *
     * @param submissionId
     * @return
     */
    public String deleteSubmissionById(String submissionId){
        try{
            Optional<SurveySubmission> submission = surveySubmissionRepository.findById(submissionId);
            if(submission.isPresent()){
                surveySubmissionRepository.deleteById(submissionId);
                return "Survey submission deleted successfully.";
            }
            else{
                return ApplicationMessages.SURVEY_RECORD_NOT_FOUND;
            }
        }
        catch (Exception ex){
            log.error("Error in deleting survey submission: "+ex.getMessage());
            return null;
        }
    }

    public RestResponse findSubmissionByJob(String job) {
        List<SurveySubmission> submissionList = new ArrayList<>();
        surveySubmissionRepository.findAll().forEach(submission -> {
            submission.getSurvey().getCompany().getJobs().forEach(job1 -> {
                if(job1.getTitle().equals(job)){
                    submissionList.add(submission);
                }
            });
        });

        return RestResponse.of(submissionList);
    }
}
