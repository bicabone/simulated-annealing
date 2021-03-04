package com.bi.service;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.annealing.TspParameter;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.model.tsp.TravellingSalesmanProblem;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspSolution;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractTspService {

  protected final ObjectiveFunction objectiveFunction;

  protected abstract SimulatedAnnealingSystem createSystem(
      TspProblem problem, ObjectiveFunction objectiveFunction, ParameterMap parameterMap);

  public TravellingSalesmanProblem solve(TspProblem problem, ParameterMap parameterMap) {
    // Create TSP system
    SimulatedAnnealingSystem simulatedAnnealingSystem =
        createSystem(problem, objectiveFunction, parameterMap);

    TravellingSalesmanProblem tsp = TravellingSalesmanProblem.builder().problem(problem).build();

    boolean storeHistory = parameterMap.getBoolean(TspParameter.STORE_HISTORY);

    // Perform simulated annealing
    while (!simulatedAnnealingSystem.isComplete()) {
      TspSolution evolve = simulatedAnnealingSystem.evolve();
      if (storeHistory) {
        tsp.getHistory().add(evolve);
      }
    }

    // Return the best solution from the system
    TspSolution solution = simulatedAnnealingSystem.getSolution();
    tsp.setSolution(solution);

    return tsp;
  }
}
