package com.bi.model.tsp;

import com.bi.util.ExchangeUtils;
import com.bi.util.RandomUtils;
import com.google.common.collect.MinMaxPriorityQueue;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Comparator;

/** TODO We need to introduce the probability of accepting a solution */
@SuppressWarnings("UnstableApiUsage")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NaiveAnnealingSystem extends SimulatedAnnealingSystem {

  private static final double SEARCH_STRENGTH = 100;
  private static final int MAX_ITERATION_COUNT = 1_000;

  private TspProblem tspProblem;

  private final MinMaxPriorityQueue<TspSolution> searches =
      MinMaxPriorityQueue.<TspSolution>orderedBy(
              Comparator.comparingDouble(a -> objectiveFunction.evaluate(a)))
          .maximumSize(1000)
          .create();

  @Builder.Default private int iteration = 0;

  @Override
  public double getTemperature() {
    return Math.exp(-1 * ++iteration / Math.pow(SEARCH_STRENGTH, 2));
  }

  @Override
  public TspSolution getSolution() {
    return searches.peekFirst();
  }

  @Override
  public void evolve() {

    if (searches.isEmpty()) {
      searches.add(tspProblem.defaultSolution());
    }

    int operationCount = getOperationCount();

    while (operationCount-- > 0) {

      /*
      TODO don't always get the head; instead sample from an exponential distribution
          and round down to the nearest non-negative int, then get that index from the search heap
          (unless it is larger than the length-1 of the heap, then get the last element).
          Let the shape of the exp dist be governed by the temp such that as the temp goes
          to 0, the index will tend towards the head.
          Either accept the chosen sample (non-head) or the head based on a probability given
      */
      TspSolution solution = getSolution();

      Pair<Integer, Integer> indices = RandomUtils.getPair(tspProblem.getStops().size() - 1);

      int first = indices.getFirst();
      int second = indices.getSecond();

      TspSolution insert = ExchangeUtils.insert(solution, first, second);
      TspSolution swap = ExchangeUtils.swap(solution, first, second);
      TspSolution inverse = ExchangeUtils.inverse(solution, first, second);

      searches.addAll(Arrays.asList(insert, swap, inverse));
    }
  }

  private int getOperationCount() {
    return (int) (getTemperature() * SEARCH_STRENGTH);
  }

  @Override
  public boolean isComplete() {
    return getOperationCount() == 0 || getIteration() > MAX_ITERATION_COUNT;
  }
}
