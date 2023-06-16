package com.serand.assignment.repository;

import com.serand.assignment.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepository extends MongoRepository<Score, String> {

}
