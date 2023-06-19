package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Candidate;
import com.serand.assignment.repository.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public RestResponse save(Candidate candidate){
        try{
            return RestResponse.of(candidateRepository.save(candidate), ApplicationMessages.CREATED_CANDIDATE_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_CREATED_CANDIDATE_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_CREATED_CANDIDATE_RECORD);
        }
    }

    /**
     *
     * @return
     */
    public RestResponse getAllCandidates() {
        try{
            return RestResponse.of(candidateRepository.findAll());
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_FETCHING_CANDIDATE_RECORD+ex.getMessage());
            return RestResponse.of(ApplicationMessages.ERROR_FETCHING_CANDIDATE_RECORD);
        }
    }

    /**
     *
     * @param candidate
     * @return
     */
    public RestResponse updateCandidate(Candidate updatedCandidate) {
        try{
            Optional<Candidate> existingCandidate = candidateRepository.findById(updatedCandidate.getId());
            if(existingCandidate.isPresent()){
                Candidate candidate = existingCandidate.get();
                candidate.setName(updatedCandidate.getName());
                candidate.setAppliedJobs(updatedCandidate.getAppliedJobs());
                candidate.setGender(updatedCandidate.getGender());
                return RestResponse.of(updatedCandidate,ApplicationMessages.SUCCESS_UPDATE_CANDIDATE_RECORD);
            }
            else{
                return RestResponse.fail(ApplicationMessages.NOT_FOUND_CANDIDATE_RECORD);
            }
        }
        catch (Exception ex){
        log.error(ApplicationMessages.ERROR_UPDATE_CANDIDATE_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_UPDATE_CANDIDATE_RECORD);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public RestResponse getCandidateById(String id) {
        try{
            Optional<Candidate> candidate = candidateRepository.findById(id);
            if(candidate.isPresent()){
                return RestResponse.of(candidate.get());
            }
            return RestResponse.fail(ApplicationMessages.NOT_FOUND_CANDIDATE_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_FETCHING_CANDIDATE_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_FETCHING_CANDIDATE_RECORD);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public RestResponse deleteCandidate(String id) {
        try{
            candidateRepository.deleteById(id);
            return RestResponse.of(ApplicationMessages.SUCCESS_DELETE_CANDIDATE_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_DELETE_CANDIDATE_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_DELETE_CANDIDATE_RECORD);
        }
    }

    /**
     *
     * @param email
     * @return
     */
    public RestResponse getCandidateByEmail(String email) {
        try{
             Candidate candidate = candidateRepository.findCandidateByEmail(email);
             if(candidate != null){
                 return RestResponse.of(candidate);
             }
             return RestResponse.fail(ApplicationMessages.NOT_FOUND_CANDIDATE_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_FETCHING_CANDIDATE_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_FETCHING_CANDIDATE_RECORD);
        }
    }
}
