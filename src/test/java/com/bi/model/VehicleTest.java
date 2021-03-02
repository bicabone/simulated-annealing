package com.bi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
  @Test
  public void test() {
    assertEquals(create(), create());
  }

  public static Vehicle create() {
    return Vehicle.builder().depot(StopTest.create()).id("Vehicle").build();
  }
}
