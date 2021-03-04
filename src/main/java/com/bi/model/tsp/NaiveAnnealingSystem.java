package com.bi.model.tsp;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.util.CopyUtils;
import com.bi.util.ExchangeUtils;
import com.bi.util.ProbabilityUtils;
import com.bi.util.RandomUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;

import java.util.Comparator;
import java.util.stream.Stream;

import static com.bi.model.annealing.AnnealingParameter.MAX_ITERATION_COUNT;
import static com.bi.model.annealing.AnnealingParameter.SEARCH_STRENGTH;

/** TODO We need to introduce the probability of accepting a solution */
@Data
@EqualsAndHashCode(callSuper = true)
public class NaiveAnnealingSystem extends SimulatedAnnealingSystem {

  private final ParameterMap parameters = new ParameterMap();

  private int iteration = 0;

  private double lastAcceptance = 0.5d;

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

    if (iteration == 0) return bestSolution.getScore();

    double delta = currentSolution.getScore() - bestSolution.getScore();

    if (delta == 0) return currentSolution.getScore() / iteration;

    return Math.abs(
        delta
            / Math.log(
                ProbabilityUtils.annealingAcceptanceProbability(
                    objectiveFunction, bestSolution, currentSolution, lastAcceptance)));
  }

  @Override
  public TspSolution getSolution() {
    return bestSolution;
  }

  @Override
  public void evolve() {

    int operationCount = getOperationCount();

    if (bestSolution.getScore() < currentSolution.getScore()) {
      currentSolution = bestSolution;
    }

    while (operationCount-- > 0) {

      TspSolution cursor = CopyUtils.clone(currentSolution);

      Pair<Integer, Integer> indices = RandomUtils.getPair(tspProblem.getStops().size() - 1);

      int i = indices.getFirst();
      int j = indices.getSecond();

      TspSolution insert = ExchangeUtils.insert(cursor, i, j).evaluate(objectiveFunction);

      TspSolution swap = ExchangeUtils.swap(cursor, i, j).evaluate(objectiveFunction);

      TspSolution inverse = ExchangeUtils.inverse(cursor, i, j).evaluate(objectiveFunction);

      TspSolution tspSolution =
          Stream.of(insert, swap, inverse)
              .min(Comparator.comparingDouble(TspSolution::getScore))
              .orElseThrow(IllegalStateException::new);

      if (acceptSolution(tspSolution)) {
        currentSolution = tspSolution;
      }

      updateBestSolution();
    }

    ++iteration;
  }

  private void updateBestSolution() {
    Double thisScore = currentSolution.getScore();
    Double bestScore = bestSolution.getScore();
    if (thisScore < bestScore) {
      bestSolution = currentSolution;
    }
  }

  boolean acceptSolution(TspSolution solution) {
    double probabilityOfAcceptance = acceptSolution(currentSolution, solution);
    boolean bernoulli = ProbabilityUtils.bernoulli(probabilityOfAcceptance);
    if (bernoulli) this.lastAcceptance = probabilityOfAcceptance;
    return bernoulli;
  }

  private int getOperationCount() {
    return getParameters().getInteger(SEARCH_STRENGTH);
  }

  @Override
  public boolean isComplete() {
    return getIteration() > getParameters().getInteger(MAX_ITERATION_COUNT);
  }

  @Override
  public ParameterMap getParameters() {
    return parameters;
  }
}
