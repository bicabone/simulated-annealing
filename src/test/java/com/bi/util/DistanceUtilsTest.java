package com.bi.util;

import com.bi.model.location.Coordinate;
import com.bi.model.location.CoordinateTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistanceUtilsTest {

  @Test
  public void testHaversineCalculation() {
    Coordinate oneStopConey = Coordinate.builder().latitude(42.9631).longitude(-85.6646).build();
    Coordinate lafayetteConey = Coordinate.builder().latitude(42.3314).longitude(-83.04887).build();
    double haversine = DistanceUtils.haversine(oneStopConey, lafayetteConey);
    Assertions.assertTrue(haversine > 0);
    Assertions.assertTrue(Math.abs(haversine - 225_412d) < 1);
  }

  @Test
  public void testHaversineCalculation_zeroIfSamePoint() {
    Coordinate coordinate = CoordinateTest.create();
    Coordinate clone = CopyUtils.clone(coordinate);
    double haversine = DistanceUtils.haversine(coordinate, clone);
    Assertions.assertTrue(haversine == 0);
  }
}
