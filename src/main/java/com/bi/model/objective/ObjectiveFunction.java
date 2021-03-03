package com.bi.model.objective;

import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspSolution;

public interface ObjectiveFunction {
  double evaluate(TspProblem problem, TspSolution solution);
}
