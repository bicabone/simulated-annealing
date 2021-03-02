package com.bi.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TspSolution extends ArrayList<Stop> {

  public TspSolution(Collection<? extends Stop> stops, Coordinate initial) {
    super(stops);
    this.initial = initial;
  }

  private Coordinate initial;
}
