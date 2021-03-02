package com.bi.model;

public interface HasCoordinates {

  Coordinate getCoordinate();

  default double getDistance(DistanceFunction distanceFunction, Coordinate location) {
    return distanceFunction.getDistance(getCoordinate(), location);
  }
}
