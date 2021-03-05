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

  public List<TravellingSalesmanProblem> getAll() {
    return mongo.find(getQuery(), TravellingSalesmanProblem.class);
  }

  public List<TravellingSalesmanProblem> get(int page, int size) {
    Query query = getQuery();
    query.skip((page - 1) * size);
    query.limit(size);
    return mongo.find(query, TravellingSalesmanProblem.class);
  }

  private Query getQuery() {
    Query query = new Query();
    query.addCriteria(Criteria.where("complete").is(true));
    query.with(Sort.by(Sort.Direction.DESC, "id"));
    query.fields().exclude("problem").exclude("solution").exclude("history");
    return query;
  }
}
