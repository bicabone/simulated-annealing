package com.bi.util;

import com.bi.model.objective.ObjectiveFunction;
import com.bi.model.tsp.TspSolution;

import java.util.Random;

public class ProbabilityUtils {

  public static boolean acceptAnnealingSolution(
      ObjectiveFunction objectiveFunction,
      TspSolution current,
      TspSolution candidate,
      double temp) {
    double probability =
        annealingAcceptanceProbability(objectiveFunction, current, candidate, temp);
    return bernoulli(probability);
  }

  public static boolean bernoulli(double probability) {
    double random = RandomUtils.getDouble(0, 1);
    return random <= probability;
  }

  public static double annealingAcceptanceProbability(
      ObjectiveFunction objectiveFunction,
      TspSolution current,
      TspSolution candidate,
      double temperature) {

    current = current.evaluate(objectiveFunction);
    candidate = candidate.evaluate(objectiveFunction);
    if (candidate.getScore() <= current.getScore()) return 1;

    return Math.exp(-1 * (candidate.getScore() - current.getScore()) / temperature);
  }

  public static double exponential(Random random, double shape) {
    double sample = RandomUtils.getDouble(random, 0, 1);
    return inverseExponentialCdf(shape, sample);
  }

  public static double exponential(double shape) {
    double sample = RandomUtils.getDouble(0, 1);
    return inverseExponentialCdf(shape, sample);
  }

  /**
   * Exponential CDF is given by
   *
   * <p>F(x; shape) = 1 - Math.exp(-1 * shape * x)
   *
   * <p>its inverse is given by
   *
   * <p>G(y; shape) = Math.log(1 - y) / (-1 * shape)
   */
  static double inverseExponentialCdf(double shape, double accumulation) {
    return Math.log(1 - accumulation) / (-1 * shape);
  }
}
