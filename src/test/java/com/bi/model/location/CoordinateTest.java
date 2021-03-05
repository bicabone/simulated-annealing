package com.bi.model.location;

import com.bi.model.location.Coordinate;
import com.bi.model.tsp.TspProblem;
import com.bi.model.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTest {

  @Test
  public void test() {
    List<Coordinate> list = createList(10);
    List<Coordinate> anotherList = createList(10);
    assertEquals(list, anotherList);
  }

  public static Coordinate create() {
    Random random = new Random(67);
    double lat = createRandom(random, 90);
    double lon = createRandom(random, 180);
    return Coordinate.builder().latitude(lat).longitude(lon).build();
  }

  public static List<Coordinate> createList(int count) {
    return createList(count, 1.0);
  }

  public static List<Coordinate> createList(int count, double range) {
    Coordinate base = create();
    return getCoordinatesNear(count, range, base);
  }

  static List<Coordinate> getCoordinatesNear(int count, double range, Coordinate base) {
    Random random = new Random(42);
    List<Coordinate> coordinates = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      Coordinate coordinate = createNearbyCoordinate(base, random, range);
      coordinates.add(coordinate);
    }
    return coordinates;
  }

  private static Coordinate createNearbyCoordinate(
      Coordinate coordinate, Random random, double range) {
    double latDelta = createRandom(random, range);
    double lonDelta = createRandom(random, range);

    return Coordinate.builder()
        .latitude(truncate(coordinate.getLatitude() + latDelta, 5))
        .longitude(truncate(coordinate.getLongitude() + lonDelta, 5))
        .build();
  }

  private static double createRandom(Random random, double range) {
    return createRandom(random, range, 5);
  }

  private static double createRandom(Random random, double range, int decimals) {
    double v = random.nextDouble() * 2 * Math.abs(range) - Math.abs(range);
    return truncate(v, decimals);
  }

  private static double truncate(double value, int decimals) {
    double multiplier = Math.pow(10, decimals);
    return Math.floor(value * multiplier) / multiplier;
  }
}
