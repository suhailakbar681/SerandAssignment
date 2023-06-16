package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Company;
import com.serand.assignment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company){
        try{
            return companyRepository.save(company);
        }
        catch (Exception ex){
            System.out.println("Error in saving Company: "+ex.getMessage());
            return null;
        }
    }

    public RestResponse createCompany(Company company) {
        Company company1 = save(company);
        if(company1 != null){
            return RestResponse.of(company1, ApplicationMessages.CREATED_COMPANY_RECORD);
        }
        return RestResponse.fail(ApplicationMessages.ERROR_CREATED_COMPANY_RECORD);
    }

    public RestResponse deleteCompany(String id) {
        try{
            companyRepository.deleteById(id);
            return RestResponse.of(ApplicationMessages.SUCCESS_DELETE_COMPANY_RECORD);
        }
        catch (Exception ex){
            System.out.println(ApplicationMessages.ERROR_DELETE_COMPANY_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_DELETE_COMPANY_RECORD);
        }
    }

    public RestResponse updateCompany(Company company) {
        try{
            Optional<Company> existingCompany = companyRepository.findById(company.getId());
            if(existingCompany.isPresent()){
                Company company1 = existingCompany.get();
                company1.setName(company.getName());
                company1.setJobs(company.getJobs());
                return RestResponse.of(company1,ApplicationMessages.SUCCESS_UPDATE_COMPANY_RECORD);
            }
            else{
                return RestResponse.fail(ApplicationMessages.NOT_FOUND_COMPANY_RECORD);
            }
        }
        catch (Exception ex){
            System.out.println(ApplicationMessages.ERROR_UPDATE_COMPANY_RECORD+ex.getMessage());
            return RestResponse.fail(ApplicationMessages.ERROR_UPDATE_COMPANY_RECORD);
        }
    }

    public RestResponse getAllCompanies() {
        try{
            return RestResponse.of(companyRepository.findAll());
        }
        catch (Exception ex){
            System.out.println(ApplicationMessages.ERROR_FETCHING_COMPANY_RECORD+ex.getMessage());
            return RestResponse.of(ApplicationMessages.ERROR_FETCHING_COMPANY_RECORD);
        }
    }
}
