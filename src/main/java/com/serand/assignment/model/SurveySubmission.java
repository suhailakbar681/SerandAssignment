package com.serand.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "survey_submission")
public class SurveySubmission {
    @Id
    private String id;
    @DBRef
    private Candidate candidate;
    @DBRef
    private Survey survey;
    private Map<String, String> responses; // Map of answer ID and answer
    // other fields, constructors, getters, setters
}
