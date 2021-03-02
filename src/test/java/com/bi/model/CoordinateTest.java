package com.bi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CoordinateTest {

  @Test
  public void test() {
    List<Coordinate> list = createList(10);
    List<Coordinate> anotherList = createList(10);
    Assertions.assertEquals(list, anotherList);
  }

  public static Coordinate create() {
    Random random = new Random(67);
    double lat = random.nextDouble();
    double lon = random.nextDouble();
    return Coordinate.builder().latitude(lat).longitude(lon).build();
  }

  public static List<Coordinate> createList(int count) {
    Random random = new Random(42);
    List<Coordinate> coordinates = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      double lat = random.nextDouble();
      double lon = random.nextDouble();
      Coordinate coordinate = Coordinate.builder().latitude(lat).longitude(lon).build();
      coordinates.add(coordinate);
    }
    return coordinates;
  }
}
