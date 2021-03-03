package com.bi.model.location;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopTest {

  @Test
  void test() {
    assertEquals(createList(10), createList(10));
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
