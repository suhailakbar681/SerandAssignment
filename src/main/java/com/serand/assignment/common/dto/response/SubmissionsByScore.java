package com.serand.assignment.common.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubmissionsByScore {
    private String submissionId;
    private String CandidateId;
    private String SurveyId;
    private Double score;
}
