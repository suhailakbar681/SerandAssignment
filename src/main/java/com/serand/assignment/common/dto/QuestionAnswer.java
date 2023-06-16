package com.serand.assignment.common.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswer {
    private String question;
    private String answer;
    private int score;
}
