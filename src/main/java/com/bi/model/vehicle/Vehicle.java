package com.bi.model.vehicle;

import com.bi.model.location.Coordinate;
import com.bi.model.location.HasCoordinates;
import com.bi.model.location.Stop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements HasCoordinates {
  private String id;
  private Stop depot;

  @Override
  @JsonIgnore
  public Coordinate getCoordinate() {
    return depot.getCoordinate();
  }
}
