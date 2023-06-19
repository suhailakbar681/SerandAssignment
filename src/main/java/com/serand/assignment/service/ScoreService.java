package com.serand.assignment.service;

import com.serand.assignment.common.ApplicationMessages;
import com.serand.assignment.common.dto.QuestionAnswer;
import com.serand.assignment.common.dto.response.RestResponse;
import com.serand.assignment.model.Score;
import com.serand.assignment.model.Survey;
import com.serand.assignment.model.SurveySubmission;
import com.serand.assignment.repository.ScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ScoreService implements ScoringService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private SurveyService surveyService;

    /**
     *
     * @param score
     * @return
     */
    public Score save(Score score){
        try{
            return scoreRepository.save(score);
        }
        catch (Exception ex){
            log.error("Error in saving score: "+ex.getMessage());
            return null;
        }
    }

    @Override
    public RestResponse calculateScoreBySubmissionId(SurveySubmission submission) {
        Survey survey = surveyService.findSurveyById(submission.getSurvey().getId());
        if(submission != null){
            Map<String, String> responses = submission.getResponses();

            int score = calculateScore(responses, survey.getSurveyQuestionsAnswers());

            Score scoreSave = new Score();
            scoreSave.setSurveySubmission(submission);
            scoreSave.setScore(score);

            Score saved = save(scoreSave);
            return RestResponse.of(saved, ApplicationMessages.SUCCESS_SCORE_CALCULATED);
        }
        return RestResponse.of(ApplicationMessages.ERROR_SCORE_CALCULATED);
    }

    @Override
    public List<Score> findSubmissionByScore(double score) {
        List<Score> scoreList = scoreRepository.findScoresByScore(score);
        return scoreList;
    }

    /**
     *
     * @param surveyResponse
     * @param surveyQuestionsAnswers
     * @return
     */
    public int calculateScore(Map<String, String> surveyResponse, Map<String, QuestionAnswer> surveyQuestionsAnswers) {
        int score = 0;

        for (Map.Entry<String, String> entry : surveyResponse.entrySet()) {
            String answerKey = entry.getKey();
            String answer = entry.getValue();

            if (surveyQuestionsAnswers.containsKey(answerKey)) {
                QuestionAnswer questionAnswer = surveyQuestionsAnswers.get(answerKey);
                int questionScore = questionAnswer.getScore();
                score += calculateQuestionScore(answerKey, answer, questionScore);
            }
        }

        return score;
    }

    /**
     *
     * @param answerKey
     * @param answer
     * @param questionScore
     * @return
     */
    private int calculateQuestionScore(String answerKey, String answer, int questionScore) {
        if (answerKey.equals("question1")) {
            if (answer.equalsIgnoreCase("blue")) {
                return questionScore;
            }
        } else if (answerKey.equals("question2")) {
            String[] languages = answer.split(",");
            int languageCount = 0;
            for (String language : languages) {
                if (language.trim().equalsIgnoreCase("java") ||
                        language.trim().equalsIgnoreCase("python") ||
                        language.trim().equalsIgnoreCase("react")) {
                    languageCount++;
                }
            }
            return languageCount * questionScore;
        } else if (answerKey.equals("question3")) {
            if (!answer.isEmpty()) {
                if (answer.equalsIgnoreCase("intellij")) {
                    return 2;
                } else {
                    return 1;
                }
            }
        } else if (answerKey.equals("question4")) {
            String[] selectedStatements = answer.split(",");
            int correctAnswers = 0;
            for (String statement : selectedStatements) {
                if (statement.trim().equals("3") || statement.trim().equals("1")) {
                    correctAnswers++;
                }
            }
            return correctAnswers * questionScore;
        }
        return 0;
    }

}
