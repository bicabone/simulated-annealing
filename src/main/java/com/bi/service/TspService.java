package com.bi.service;

import com.bi.model.objective.ObjectiveFunction;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspSolution;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TspService {

  protected final ObjectiveFunction objectiveFunction;

  protected abstract SimulatedAnnealingSystem createSystem(
      TspProblem problem, ObjectiveFunction objectiveFunction);

  public TspSolution solve(TspProblem problem) {
    // Create TSP system
    SimulatedAnnealingSystem simulatedAnnealingSystem = createSystem(problem, objectiveFunction);

    // Perform simulated annealing
    while (!simulatedAnnealingSystem.isComplete()) {
      simulatedAnnealingSystem.evolve();
    }

    // Return the best solution from the system
    return simulatedAnnealingSystem.getSolution();
  }
}
