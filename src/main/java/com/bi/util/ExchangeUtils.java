package com.bi.util;

import com.bi.model.location.Stop;
import com.bi.model.tsp.TspSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExchangeUtils {

  private ExchangeUtils() {}

  /**
   * (1,2,3,4,5,6), i=1,j=4
   *
   * <p>produces
   *
   * <p>(1,5,3,4,2,6)
   */
  public static TspSolution swap(TspSolution solution, int i, int j) {
    Stop[] stops = solution.getStops().toArray(new Stop[] {});
    Stop first = stops[i];
    Stop last = stops[j];
    stops[i] = last;
    stops[j] = first;
    return new TspSolution(Arrays.asList(stops), solution.getInitial());
  }

  /**
   * (1,2,3,4,5,6), i=1,j=4
   *
   * <p>produces
   *
   * <p>(1,5,4,3,2,6)
   */
  public static TspSolution inverse(TspSolution solution, int i, int j) {
    List<Stop> copy = new ArrayList<>(solution.getStops());
    List<Stop> list = copy.subList(i, j);
    Collections.reverse(list);
    return new TspSolution(copy, solution.getInitial());
  }

  /**
   * (1,2,3,4,5,6), i=1,j=4
   *
   * <p>produces
   *
   * <p>(1,3,4,2,5,6)
   */
  public static TspSolution insert(TspSolution solution, int i, int j) {
    List<Stop> copy = new ArrayList<>(solution.getStops());
    Stop remove = copy.remove(i);
    copy.add(j - 1, remove);
    return new TspSolution(copy, solution.getInitial());
  }
}
