package com.bi.model.tsp;

import com.bi.model.location.Coordinate;
import com.bi.model.location.Stop;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TspSolution extends ArrayList<Stop> {

  private Coordinate initial;

  @Builder
  public TspSolution(Collection<? extends Stop> stops, Coordinate initial) {
    super(stops);
    this.initial = initial;
  }
}
