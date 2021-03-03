package com.bi.model.distance;

import com.bi.model.location.Coordinate;

public interface DistanceFunction {
  double getDistance(Coordinate a, Coordinate b);
}
