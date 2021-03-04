package com.bi.util;

import com.bi.model.location.Stop;
import com.bi.model.tsp.TspSolution;
import com.bi.model.tsp.TravellingSalesmanProblemSolutionTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeUtilsTest {

  @Test
  public void swap() {
    TspSolution solution = TravellingSalesmanProblemSolutionTest.create();
    TspSolution swapped = ExchangeUtils.swap(solution, 1, 4);
    // Swapped
    assertEquals(solution.getStops().get(4), swapped.getStops().get(1));
    assertEquals(solution.getStops().get(1), swapped.getStops().get(4));
    // Not swapped
    assertEquals(solution.getStops().get(0), swapped.getStops().get(0));
    assertEquals(solution.getStops().get(2), swapped.getStops().get(2));
    assertEquals(solution.getStops().get(3), swapped.getStops().get(3));
    assertEquals(solution.getStops().get(5), swapped.getStops().get(5));
  }

  @Test
  void inverse() {
    TspSolution solution = TravellingSalesmanProblemSolutionTest.create();
    TspSolution inverted = ExchangeUtils.inverse(solution, 3, 8);
    List<Stop> stops = inverted.getStops().subList(3, 8);
    ArrayList<Stop> expected = new ArrayList<>(solution.getStops().subList(3, 8));
    Collections.reverse(expected);
    assertEquals(expected, stops);
    assertEquals(solution.getStops().subList(0, 2), inverted.getStops().subList(0, 2));
    assertEquals(solution.getStops().subList(9, 20), inverted.getStops().subList(9, 20));
  }

  @Test
  void insert() {

    TspSolution solution = TravellingSalesmanProblemSolutionTest.create();
    TspSolution insert = ExchangeUtils.insert(solution, 1, 4);
    List<Stop> stops1 = solution.getStops();
    List<Stop> stops2 = insert.getStops();
    assertEquals(stops1.get(0), stops2.get(0));
    assertEquals(stops1.get(2), stops2.get(1));
    assertEquals(stops1.get(3), stops2.get(2));
    assertEquals(stops1.get(1), stops2.get(3));
    assertEquals(stops1.get(4), stops2.get(4));
  }
}
