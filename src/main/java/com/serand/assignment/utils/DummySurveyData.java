package com.serand.assignment.utils;

import com.serand.assignment.common.dto.QuestionAnswer;
import com.serand.assignment.common.enumerate.Gender;
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
        QuestionAnswer questionAnswer = new QuestionAnswer();

        questionAnswer.setQuestion("What is your favorite color?");
        questionAnswer.setAnswer("Blue");
        questionAnswer.setScore(1);
        surveyQuestionsAnswers.put("question1",questionAnswer);

        questionAnswer.setQuestion("What is your preferred programming language?");
        questionAnswer.setAnswer("Java, Python, React");
        questionAnswer.setScore(1);
        surveyQuestionsAnswers.put("question2",questionAnswer);

        questionAnswer.setQuestion("what is your favourite ide?");
        questionAnswer.setAnswer("IntelliJ");
        questionAnswer.setScore(2);
        surveyQuestionsAnswers.put("question3",questionAnswer);

        questionAnswer.setQuestion("Select two statements which match your values.\n" +
                "1. Entrepreneurial\n" +
                "2. Detail Oriented\n" +
                "3. Disruptive\n" +
                "4. Survival of the fittest\n" +
                "5. By any means necessary");
        questionAnswer.setAnswer("1, 3");
        questionAnswer.setScore(2);
        surveyQuestionsAnswers.put("question4",questionAnswer);

        Survey survey = new Survey();
        survey.setTitle("Demo Survey_"+UUID.randomUUID());
        survey.setCompany(getDummyCompany(companyService, jobService));
        survey.setSurveyQuestionsAnswers(surveyQuestionsAnswers);

        surveyService.createSurvey(survey);

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
