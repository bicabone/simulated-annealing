package com.bi.util;

import com.bi.model.location.Coordinate;
import com.bi.model.location.CoordinateTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CopyUtilsTest {

  @Test
  public void testClone() {
    Coordinate coordinate = CoordinateTest.create();
    Coordinate clone = CopyUtils.clone(coordinate);
    assertEquals(coordinate, clone);
  }
}
