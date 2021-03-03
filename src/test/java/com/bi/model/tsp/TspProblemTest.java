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
    return TspProblem.builder()
        .stops(StopTest.createList(20))
        .vehicle(VehicleTest.create())
        .build();
  }
}
