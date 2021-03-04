package com.bi.model.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonIgnore
  public Coordinate getCoordinate() {
    return new Coordinate(latitude, longitude);
  }
}
