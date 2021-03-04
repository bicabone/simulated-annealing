package com.bi.model.tsp;

import com.bi.model.location.Coordinate;
import com.bi.model.location.Stop;
import com.bi.model.objective.ObjectiveFunction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TspSolution {

  private Coordinate initial;

  private List<Stop> stops;

  private Double score = null;

  @Builder
  public TspSolution(List<Stop> stops, Coordinate initial) {
    this.stops = stops;
    this.initial = initial;
  }

  public TspSolution evaluate(ObjectiveFunction objectiveFunction) {
    if (this.score == null) {
      this.score = objectiveFunction.evaluate(this);
    }
    return this;
  }
}
