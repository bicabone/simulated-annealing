package com.bi.util;

import com.bi.model.location.Stop;
import com.bi.model.tsp.TspSolution;
import com.bi.model.tsp.TspSolutionTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeUtilsTest {

  @Test
  public void swap() {
    TspSolution solution = TspSolutionTest.create();
    TspSolution swapped = ExchangeUtils.swap(solution, 1, 4);
    // Swapped
    assertEquals(solution.get(4), swapped.get(1));
    assertEquals(solution.get(1), swapped.get(4));
    // Not swapped
    assertEquals(solution.get(0), swapped.get(0));
    assertEquals(solution.get(2), swapped.get(2));
    assertEquals(solution.get(3), swapped.get(3));
    assertEquals(solution.get(5), swapped.get(5));
  }

  @Test
  void inverse() {
    TspSolution solution = TspSolutionTest.create();
    TspSolution inverted = ExchangeUtils.inverse(solution, 3, 8);
    List<Stop> stops = inverted.subList(3, 8);
    ArrayList<Stop> expected = new ArrayList<>(solution.subList(3, 8));
    Collections.reverse(expected);
    assertEquals(expected, stops);
    assertEquals(solution.subList(0, 2), inverted.subList(0, 2));
    assertEquals(solution.subList(9, 20), inverted.subList(9, 20));
  }

  @Test
  void insert() {

    TspSolution solution = TspSolutionTest.create();
    TspSolution insert = ExchangeUtils.insert(solution, 1, 4);
    assertEquals(solution.get(0), insert.get(0));
    assertEquals(solution.get(2), insert.get(1));
    assertEquals(solution.get(3), insert.get(2));
    assertEquals(solution.get(1), insert.get(3));
    assertEquals(solution.get(4), insert.get(4));
  }
}
