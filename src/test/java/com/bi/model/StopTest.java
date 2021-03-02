package com.bi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StopTest {

  @Test
  void test() {
    Assertions.assertEquals(createList(10), createList(10));
  }

  public static Stop create() {
    Coordinate coordinate = CoordinateTest.create();
    return Stop.builder()
        .id("depot")
        .latitude(coordinate.getLatitude())
        .longitude(coordinate.getLongitude())
        .build();
  }

  public static List<Stop> createList(int count) {
    List<Coordinate> list = CoordinateTest.createList(count);
    List<Stop> stops = new ArrayList<>(count);
    for (int i = 0; i < list.size(); i++) {
      Coordinate coordinate = list.get(i);
      Stop stop =
          Stop.builder()
              .id("stop_" + i)
              .latitude(coordinate.getLatitude())
              .longitude(coordinate.getLongitude())
              .build();
      stops.add(stop);
    }
    return stops;
  }
}
