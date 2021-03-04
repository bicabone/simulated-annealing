package com.bi.model.objective;

import com.bi.model.distance.HaversineCalculator;
import com.bi.model.location.Stop;
import com.bi.model.tsp.TspSolution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@AllArgsConstructor
public class DistanceObjective implements ObjectiveFunction {

  private final HaversineCalculator haversineCalculator;

  @Override
  public double evaluate(TspSolution solution) {

    double sum = 0d;
    Iterator<Stop> it = solution.getStops().iterator();

    if (!it.hasNext()) return sum;

    Stop last = it.next();

    // Initial
    sum += haversineCalculator.getDistance(last, solution.getInitial());

    // Intermediate
    while (it.hasNext()) {
      Stop next = it.next();
      sum += haversineCalculator.getDistance(last, next);
      last = next;
    }

    // Final
    sum += haversineCalculator.getDistance(last, solution.getInitial());

    return sum;
  }
}
