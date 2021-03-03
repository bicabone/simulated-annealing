package com.bi.model.location;

import com.bi.model.distance.DistanceFunction;

public interface HasCoordinates {

  Coordinate getCoordinate();

  default double getDistance(DistanceFunction distanceFunction, Coordinate location) {
    return distanceFunction.getDistance(getCoordinate(), location);
  }
}
