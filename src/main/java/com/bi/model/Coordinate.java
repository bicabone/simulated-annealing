package com.bi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate implements HasCoordinates {
  private double latitude;
  private double longitude;

  @Override
  public Coordinate getCoordinate() {
    return this;
  }
}
