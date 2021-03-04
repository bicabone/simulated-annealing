package com.bi.repository;

import com.bi.model.tsp.TravellingSalesmanProblem;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TspRepository {

  private final MongoTemplate mongo;

  public TravellingSalesmanProblem save(TravellingSalesmanProblem problem) {
    return mongo.save(problem);
  }

  public TravellingSalesmanProblem get(String id) {
    return mongo.findById(id, TravellingSalesmanProblem.class);
  }
}
