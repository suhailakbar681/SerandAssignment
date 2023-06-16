package com.serand.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "score")
public class Score {
    @Id
    private String id;
    @DBRef
    private SurveySubmission surveySubmission;
    private double score;
    // other fields, constructors, getters, setters
}
