package com.serand.assignment.controller;

import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Company;
import com.serand.assignment.model.Survey;
import com.serand.assignment.service.CompanyService;
import com.serand.assignment.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/save")
    public RestResponse createCompany(@Validated @RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    public RestResponse deleteCompany(@Validated @PathVariable("id") String id) {
        return companyService.deleteCompany(id);
    }

    @PutMapping("/update")
    public RestResponse updateCompany(@Validated @RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @GetMapping("/all")
    public RestResponse findAll(){
        return companyService.getAllCompanies();
    }

}
