package com.bi.util;

import java.util.Random;

public class ProbabilityUtils {

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
