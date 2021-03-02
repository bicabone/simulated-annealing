package com.bi.model;

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
  public Coordinate getCoordinate() {
    return depot.getCoordinate();
  }
}