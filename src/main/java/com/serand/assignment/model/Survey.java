package com.serand.assignment.model;
import com.serand.assignment.common.dto.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "surveys")
public class Survey {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    @DBRef
    private Company company; // Reference to the company
    Map<String, QuestionAnswer> surveyQuestionsAnswers;

}
