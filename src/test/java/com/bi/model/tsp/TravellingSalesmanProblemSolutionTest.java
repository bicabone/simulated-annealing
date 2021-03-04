package com.bi.model.tsp;

import com.bi.util.CopyUtils;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TravellingSalesmanProblemSolutionTest {

  @Test
 public void test() {
    TspSolution expected = create();
    assertEquals(expected, create());
    assertNotEquals(expected, createRandom());
  }

  public static TspSolution createRandom() {
    return create(new Random());
  }

  public static TspSolution create() {
    return create(new Random(42));
  }

  public static TspSolution create(Random random) {
    TspProblem tspProblem = TspProblemTest.create();
    TspProblem clone = CopyUtils.clone(tspProblem);
    Collections.shuffle(clone.getStops(), random);
    return new TspSolution(clone.getStops(), clone.getVehicle().getDepot());
  }
}
