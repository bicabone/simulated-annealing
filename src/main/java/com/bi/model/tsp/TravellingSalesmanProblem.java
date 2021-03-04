package com.bi.model.tsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TravellingSalesmanProblem {
  @Id private String id;
  @Builder.Default private String name = UUID.randomUUID().toString();
  private TspProblem problem;
  private TspSolution solution;
  @Builder.Default private List<TspSolution> history = new ArrayList<>();
}
