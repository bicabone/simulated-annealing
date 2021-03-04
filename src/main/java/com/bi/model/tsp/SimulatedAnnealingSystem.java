package com.bi.model.tsp;

import com.bi.model.annealing.HasTemperature;
import com.bi.model.annealing.ParameterMap;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.util.ProbabilityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class SimulatedAnnealingSystem implements HasTemperature {

  protected TspProblem tspProblem;

  protected ObjectiveFunction objectiveFunction;

  public abstract TspSolution getSolution();

  public abstract void evolve();

  public abstract boolean isComplete();

  public abstract ParameterMap getParameters();

  public double acceptSolution(TspSolution current, TspSolution candidate) {
    return ProbabilityUtils.annealingAcceptanceProbability(
            getObjectiveFunction(), current, candidate, getTemperature());
  }
}
