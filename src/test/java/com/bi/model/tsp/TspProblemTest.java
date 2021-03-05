package com.bi.model.tsp;

import com.bi.model.location.Coordinate;
import com.bi.model.location.Stop;
import com.bi.model.vehicle.Vehicle;
import com.bi.model.vehicle.VehicleTest;
import com.bi.model.location.StopTest;
import com.bi.util.DistanceUtils;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TspProblemTest {

  @Test
  public void test() {
    assertEquals(create(), create());
  }

  public static TspProblem create() {
    return create(20);
  }

  public static TspProblem create(int count) {
    return TspProblem.builder()
        .name("Test")
        .stops(StopTest.createList(count))
        .vehicle(VehicleTest.create())
        .build();
  }

  @Test
  public void detroit() {
    // 42.3314° N, 83.0458° W
    Coordinate annArbor = Coordinate.builder().latitude(42.2808).longitude(-83.7430).build();
    Stop depot =
        Stop.builder()
            .id("Depot")
            .latitude(annArbor.getLatitude())
            .longitude(annArbor.getLongitude())
            .build();

    // 1 deg of lat lon is about 70 miles; lon is about 55
    List<Stop> stops = StopTest.createStopsNear(100, 0.3, depot);
    TspProblem problem =
        TspProblem.builder()
            .name("Ann_Arbor_" + UUID.randomUUID().toString())
            .vehicle(
                Vehicle.builder()
                    .id("Vehicle_" + UUID.randomUUID().toString())
                    .depot(depot)
                    .build())
            .stops(stops)
            .build();

    // about 1600 meters per mile
    int threshold = 70 * 1700;
    double asDouble =
        stops.stream()
            .map(s -> DistanceUtils.haversine(s, depot))
            .mapToDouble(x -> x)
            .max()
            .getAsDouble();
    assertTrue(asDouble < threshold);

    String example = new Gson().toJson(problem);
    assertNotNull(example);
  }
}
