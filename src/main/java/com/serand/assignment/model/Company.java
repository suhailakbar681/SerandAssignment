package com.serand.assignment.model;
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
@Document(collection = "companies")
public class Company {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    @DBRef
    private List<Job> jobs;
    // Other company properties, getters, and setters
}
