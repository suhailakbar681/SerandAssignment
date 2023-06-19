package com.serand.assignment.repository;

import com.serand.assignment.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScoreRepository extends MongoRepository<Score, String> {

    List<Score> findScoresByScore(double score);

}
