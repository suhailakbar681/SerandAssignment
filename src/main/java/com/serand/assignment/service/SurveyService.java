package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Survey;
import com.serand.assignment.repository.SurveyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    /**
     *
     * @param survey
     * @return
     */
    public RestResponse createSurvey(Survey survey){
        try{
            return RestResponse.of(surveyRepository.save(survey),ApplicationMessages.CREATED_SURVEY_RECORD);
        }
        catch (Exception ex){
            log.info(ApplicationMessages.ERROR_CREATED_SURVEY_RECORD + ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_CREATED_SURVEY_RECORD);
        }
    }

    /**
     *
     * @param surveyId
     * @return
     */
    public RestResponse deleteSurvey(String surveyId){
        try{
            Optional<Survey> survey = surveyRepository.findById(surveyId);
            if(survey.isPresent()){
                surveyRepository.deleteById(surveyId);
                return RestResponse.success(ApplicationMessages.SUCCESS_DELETE_SURVEY_RECORD);
            }
            else{
                return RestResponse.fail(ApplicationMessages.SURVEY_RECORD_NOT_FOUND);
            }
        }
        catch (Exception ex){
            log.info(ApplicationMessages.ERROR_DELETE_SURVEY_RECORD + ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_DELETE_SURVEY_RECORD + ex.getMessage());
        }
    }

    /**
     *
     * @param updatedSurvey
     * @return
     */
    public RestResponse updateSurvey(Survey updatedSurvey){
        try{
            Optional<Survey> existingSurvey = surveyRepository.findById(updatedSurvey.getId());
            if(existingSurvey.isPresent()){
                Survey survey = existingSurvey.get();
                survey.setTitle(updatedSurvey.getTitle());
                survey.setCompany(updatedSurvey.getCompany());
                survey.setSurveyQuestionsAnswers(updatedSurvey.getSurveyQuestionsAnswers());
                surveyRepository.save(survey);
                return RestResponse.of(updatedSurvey,ApplicationMessages.SUCCESS_UPDATE_SURVEY_RECORD);
            }
            else{
                return RestResponse.fail(ApplicationMessages.SURVEY_RECORD_NOT_FOUND);
            }
        }
        catch (Exception ex){
            log.info(ApplicationMessages.ERROR_UPDATE_SURVEY_RECORD + ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_UPDATE_SURVEY_RECORD);
        }
    }

    /**
     *
     * @return
     */
    public RestResponse getAllSurveys(){
        try{
            return RestResponse.of(surveyRepository.findAll());
        }
        catch (Exception ex){
            log.info(ApplicationMessages.ERROR_FETCHING_SURVEY_RECORD + ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_FETCHING_SURVEY_RECORD);
        }
    }

    /**
     *
     * @param companyId
     * @return
     */
    public RestResponse getSurveysByCompanyId(String companyId){
        try{
            return RestResponse.of(surveyRepository.findByCompanyId(companyId));
        }
        catch (Exception ex){
            log.info(ApplicationMessages.ERROR_FETCHING_SURVEY_RECORD + ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_FETCHING_SURVEY_RECORD);
        }
    }

    public Survey findSurveyById(String surveyId){
        try{
            Optional<Survey> survey = surveyRepository.findById(surveyId);
            if(survey.isPresent()){
                return survey.get();
            }
            else{
                log.info(ApplicationMessages.SURVEY_RECORD_NOT_FOUND);
                return null;
            }
        }
        catch (Exception ex){
            log.info(ApplicationMessages.ERROR_FETCHING_SURVEY_RECORD + ex.getMessage());
            return null;
        }
    }
}
