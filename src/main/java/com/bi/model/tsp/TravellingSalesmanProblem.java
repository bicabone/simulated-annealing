package com.bi.model.tsp;

import com.bi.model.annealing.ParameterMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
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

  @Builder.Default private ZonedDateTime creationTime = ZonedDateTime.now();

  private ParameterMap parameterMap;

  private TspProblem problem;

  private TspSolution solution;

  @Builder.Default private boolean complete = false;

  @Builder.Default private List<TspSolution> history = new ArrayList<>();
}
