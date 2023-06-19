package com.serand.assignment.controller;


import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Survey;
import com.serand.assignment.repository.SurveyRepository;
import com.serand.assignment.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Responsible for all user related endpoints.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/surveys")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@PostMapping("/save")
	public RestResponse createSurvey(@Validated @RequestBody Survey survey) {
		return surveyService.createSurvey(survey);
	}

	@DeleteMapping("/delete/{id}")
	public RestResponse deleteSurvey(@Validated @PathVariable("id") String id) {
		return surveyService.deleteSurvey(id);
	}

	@GetMapping("/company/{companyId}")
	public RestResponse getSurveysByCompanyId(@Validated @PathVariable("companyId") String companyId) {
		return surveyService.getSurveysByCompanyId(companyId);
	}

	@PutMapping("/update")
	public RestResponse updateSurvey(@Validated @RequestBody Survey survey){
		return surveyService.updateSurvey(survey);
	}

	@GetMapping("/all")
	public RestResponse findAll(){
		return surveyService.getAllSurveys();
	}

}