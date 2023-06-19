package com.serand.assignment.utils;

import com.serand.assignment.common.dto.QuestionAnswer;
import com.serand.assignment.common.enums.Gender;
import com.serand.assignment.model.*;
import com.serand.assignment.service.CandidateService;
import com.serand.assignment.service.CompanyService;
import com.serand.assignment.service.JobService;
import com.serand.assignment.service.SurveyService;

import java.util.*;

public class DummySurveyData {
    /**
     *
     * @param surveyService
     * @param companyService
     * @param jobService
     * @return
     */
    public static Survey getDummySurvey(
            SurveyService surveyService,
            CompanyService companyService,
            JobService jobService
    ) {
        Map<String, QuestionAnswer> surveyQuestionsAnswers = new HashMap<>();

        QuestionAnswer firstQA = new QuestionAnswer();

        firstQA.setQuestion("What is your favorite color?");
        firstQA.setAnswer("Blue");
        firstQA.setScore(1);
        surveyQuestionsAnswers.put("question1",firstQA);

        QuestionAnswer secondQA = new QuestionAnswer();

        secondQA.setQuestion("What is your preferred programming language?");
        secondQA.setAnswer("Java, Python, React");
        secondQA.setScore(1);
        surveyQuestionsAnswers.put("question2",secondQA);

        QuestionAnswer thirdQA = new QuestionAnswer();

        thirdQA.setQuestion("what is your favourite ide?");
        thirdQA.setAnswer("IntelliJ");
        thirdQA.setScore(2);
        surveyQuestionsAnswers.put("question3",thirdQA);

        QuestionAnswer fourthQA = new QuestionAnswer();

        fourthQA.setQuestion("Select two statements which match your values.\n" +
                "1. Entrepreneurial\n" +
                "2. Detail Oriented\n" +
                "3. Disruptive\n" +
                "4. Survival of the fittest\n" +
                "5. By any means necessary");
        fourthQA.setAnswer("1, 3");
        fourthQA.setScore(2);
        surveyQuestionsAnswers.put("question4",fourthQA);

        Survey survey = new Survey();
        survey.setTitle("Demo Survey_"+UUID.randomUUID());
        survey.setCompany(getDummyCompany(companyService, jobService));
        survey.setSurveyQuestionsAnswers(surveyQuestionsAnswers);

        return (Survey) surveyService.createSurvey(survey).getData();
    }

    /**
     *
     * @param companyService
     * @param jobService
     * @return
     */
    public static Company getDummyCompany(
            CompanyService companyService,
            JobService jobService
    ) {
        Company company = new Company();
        company.setName("Demo Company_" + UUID.randomUUID());
        company.setJobs(getListOfDummyJobs(jobService));

        return companyService.save(company);
    }

    /**
     *
     * @param jobService
     * @return
     */
    public static List<Job> getListOfDummyJobs(JobService jobService) {
        Job job1 = new Job();
        job1.setTitle("Dummy Job_" + UUID.randomUUID());

        Job job2 = new Job();
        job2.setTitle("Dummy Job_" + UUID.randomUUID());

        Job job3 = new Job();
        job3.setTitle("Dummy Job_" + UUID.randomUUID());

        List<Job> jobList = new ArrayList<>();

        jobList.add((Job) jobService.save(job1).getData());
        jobList.add((Job) jobService.save(job2).getData());
        jobList.add((Job) jobService.save(job3).getData());

        return jobList;
    }

    /**
     *
     * @param candidateService
     * @return
     */
    public static Candidate getNewDummyCandidate(CandidateService candidateService) {
        Candidate candidate = new Candidate();
        candidate.setName("Suhail Akbar");
        candidate.setGender(Gender.MALE);
        candidate.setEmail("suhail.akbar" + UUID.randomUUID() + "@gmail.com");
        candidate.setAppliedJobs(null);
        return (Candidate) candidateService.save(candidate).getData();
    }
}
