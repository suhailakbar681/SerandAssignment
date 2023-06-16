package com.serand.assignment.model;
import com.serand.assignment.common.enumerate.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "candidates")
public class Candidate {
    @Id
    private String id;
    private String name;
//    private String candidates;
    private Gender gender;
    @Indexed(unique = true)
    private String email;
    @DBRef
    private List<Job> appliedJobs;
}
