package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Company;
import com.serand.assignment.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company){
        try{
            return companyRepository.save(company);
        }
        catch (Exception ex){
            log.error("Error in saving Company: "+ex.getMessage());
            return null;
        }
    }

    public RestResponse createCompany(Company company) {
        Company savedCompany = save(company);
        if(savedCompany != null){
            return RestResponse.of(savedCompany, ApplicationMessages.CREATED_COMPANY_RECORD);
        }
        return RestResponse.fail(ApplicationMessages.ERROR_CREATED_COMPANY_RECORD);
    }

    public RestResponse deleteCompany(String id) {
        try{
            companyRepository.deleteById(id);
            return RestResponse.of(ApplicationMessages.SUCCESS_DELETE_COMPANY_RECORD);
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_DELETE_COMPANY_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_DELETE_COMPANY_RECORD);
        }
    }

    public RestResponse updateCompany(Company updatedCompany) {
        try{
            Optional<Company> existingCompany = companyRepository.findById(updatedCompany.getId());
            if(existingCompany.isPresent()){
                Company company = existingCompany.get();
                company.setName(updatedCompany.getName());
                company.setJobs(updatedCompany.getJobs());
                return RestResponse.of(updatedCompany,ApplicationMessages.SUCCESS_UPDATE_COMPANY_RECORD);
            }
            else{
                return RestResponse.fail(ApplicationMessages.NOT_FOUND_COMPANY_RECORD);
            }
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_UPDATE_COMPANY_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_UPDATE_COMPANY_RECORD);
        }
    }

    public RestResponse getAllCompanies() {
        try{
            return RestResponse.of(companyRepository.findAll());
        }
        catch (Exception ex){
            log.error(ApplicationMessages.ERROR_FETCHING_COMPANY_RECORD+ex.getMessage());
            return RestResponse.of(ApplicationMessages.ERROR_FETCHING_COMPANY_RECORD);
        }
    }
}
