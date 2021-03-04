package com.bi.model.tsp;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.util.CopyUtils;
import com.bi.util.ExchangeUtils;
import com.bi.util.RandomUtils;
import com.google.common.collect.MinMaxPriorityQueue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Comparator;

import static com.bi.model.annealing.AnnealingParameter.MAX_ITERATION_COUNT;
import static com.bi.model.annealing.AnnealingParameter.SEARCH_STRENGTH;

/** TODO We need to introduce the probability of accepting a solution */
@SuppressWarnings("UnstableApiUsage")
@Data
@EqualsAndHashCode(callSuper = true)
public class NaiveAnnealingSystem extends SimulatedAnnealingSystem {

  private final ParameterMap parameters = new ParameterMap();

  private final MinMaxPriorityQueue<TspSolution> searches =
      MinMaxPriorityQueue.<TspSolution>orderedBy(
              Comparator.comparingDouble(a -> objectiveFunction.evaluate(a)))
          .maximumSize(1000)
          .create();

  private int iteration = 0;

  private TspSolution bestSolution;
  private TspSolution currentSolution;

  public NaiveAnnealingSystem(
      TspProblem tspProblem,
      ObjectiveFunction objectiveFunction,
      double searchStrength,
      int maxIterationCount) {
    super(tspProblem, objectiveFunction);
    TspSolution solution = tspProblem.defaultSolution();
    bestSolution = CopyUtils.clone(solution).evaluate(objectiveFunction);
    currentSolution = CopyUtils.clone(solution).evaluate(objectiveFunction);
    this.parameters.put(MAX_ITERATION_COUNT, maxIterationCount);
    this.parameters.put(SEARCH_STRENGTH, searchStrength);
  }

  @Override
  public double getTemperature() {
    double searchStrength = getParameters().getDouble(SEARCH_STRENGTH);
    return Math.exp(-1 * ++iteration / Math.pow(searchStrength, 2));
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
    double searchStrength = getParameters().getDouble(SEARCH_STRENGTH);
    return (int) (getTemperature() * searchStrength);
  }

  @Override
  public boolean isComplete() {
    int maxIterationCount = getParameters().getInteger(MAX_ITERATION_COUNT);
    return getOperationCount() == 0 || getIteration() > maxIterationCount;
  }

  @Override
  public ParameterMap getParameters() {
    return parameters;
  }
}
