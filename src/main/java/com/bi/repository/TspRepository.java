package com.bi.repository;

import com.bi.model.tsp.TravellingSalesmanProblem;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

  public List<TravellingSalesmanProblem> get(int page, int size) {
    Query query = new Query();
    query
        .addCriteria(Criteria.where("complete").is(true))
        .with(Sort.by(Sort.Direction.DESC, "id"))
        .skip((page - 1) * size)
        .limit(size)
        .fields()
        .exclude("problem")
        .exclude("solution")
        .exclude("history");
    return mongo.find(query, TravellingSalesmanProblem.class);
  }
}
