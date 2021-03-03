package com.bi.common;

import com.bi.model.Coordinate;
import com.bi.model.CoordinateTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DistanceUtilsTest {

  @Test
  public void testHaversineCalculation() {

    Coordinate oneStopConey = Coordinate.builder().latitude(42.9631).longitude(-85.6646).build();
    Coordinate lafayetteConey = Coordinate.builder().latitude(42.3314).longitude(-83.04887).build();

    double haversine = DistanceUtils.haversine(oneStopConey, lafayetteConey);

    Assertions.assertTrue(haversine > 0);
    Assertions.assertTrue(Math.abs(haversine - 225_413d) < 1);
  }
}
