package com.serand.assignment.controller;


import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Candidate;
import com.serand.assignment.model.Survey;
import com.serand.assignment.repository.CandidateRepository;
import com.serand.assignment.repository.SurveyRepository;
import com.serand.assignment.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Responsible for all candidate related endpoints.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidates")
public class CandidateController {
	@Autowired
	private CandidateService candidateService;

	@PostMapping("/save")
	public RestResponse createCandidate(@Validated @RequestBody Candidate candidate) {
		return candidateService.save(candidate);
	}

	@DeleteMapping("/delete/{id}")
	public RestResponse deleteCandidate(@Validated @PathVariable("id") String id) {
		return candidateService.deleteCandidate(id);
	}

	@GetMapping("/get/id/{candidateId}")
	public RestResponse getCandidateById(@Validated @PathVariable("candidateId") String candidateId) {
		return candidateService.getCandidateById(candidateId);
	}

	@GetMapping("/get/email/{email}")
	public RestResponse getCandidateByEmail(@Validated @PathVariable("email") String email) {
		return candidateService.getCandidateByEmail(email);
	}

	@PutMapping("/update")
	public RestResponse updateCandidate(@Validated @RequestBody Candidate candidate){
		return candidateService.updateCandidate(candidate);
	}

	@GetMapping("/all")
	public RestResponse findAll(){
		return candidateService.getAllCandidates();
	}

}