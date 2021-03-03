package com.bi.model.distance;

import com.bi.util.DistanceUtils;
import com.bi.model.location.Coordinate;

public class HaversineCalculator implements DistanceFunction {
  @Override
  public double getDistance(Coordinate a, Coordinate b) {
    return DistanceUtils.haversine(a, b);
  }
}
