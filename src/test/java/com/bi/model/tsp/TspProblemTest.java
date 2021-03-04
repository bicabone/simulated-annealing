package com.bi.model.tsp;

import com.bi.model.vehicle.VehicleTest;
import com.bi.model.location.StopTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
