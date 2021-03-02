package com.bi.model;

public interface ObjectiveFunction {
  double evaluate(TspProblem problem, TspSolution solution);
}
