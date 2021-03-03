package com.bi.model.vehicle;

import com.bi.model.location.StopTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
  @Test
  public void test() {
    assertEquals(create(), create());
  }

  public static Vehicle create() {
    return Vehicle.builder().depot(StopTest.create()).id("Vehicle").build();
  }
}
