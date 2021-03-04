package com.bi.service;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.annealing.TspParameter;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import com.bi.model.tsp.TravellingSalesmanProblem;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspSolution;
import com.bi.repository.TspRepository;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@AllArgsConstructor
public abstract class AbstractTspService {

  public static final ExecutorService EXECUTOR =
      Executors.newFixedThreadPool(
          4, new ThreadFactoryBuilder().setNameFormat("TspWorker-%d").build());

  protected final ObjectiveFunction objectiveFunction;
  private final TspRepository repository;

  protected abstract SimulatedAnnealingSystem createSystem(
      TspProblem problem, ObjectiveFunction objectiveFunction, ParameterMap parameterMap);

  abstract ParameterMap getDefaultParameters();

  public TravellingSalesmanProblem solve(TspProblem problem) {
    return solve(problem, getDefaultParameters());
  }

  public TravellingSalesmanProblem solve(TspProblem problem, ParameterMap parameterMap) {
    TravellingSalesmanProblem tsp = TravellingSalesmanProblem.builder().problem(problem).build();
    TravellingSalesmanProblem persisted = repository.save(tsp);
    EXECUTOR.submit(() -> solve(problem, parameterMap, persisted));
    return persisted;
  }

  protected void solve(
      TspProblem problem, ParameterMap parameterMap, TravellingSalesmanProblem tsp) {
    log.info("Solving TSP:{}", tsp.getName());

    // Create TSP system
    SimulatedAnnealingSystem simulatedAnnealingSystem =
        createSystem(problem, objectiveFunction, parameterMap);

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

    tsp = repository.save(tsp);
    log.info("Finished solving TSP: {}, id: {}", tsp.getName(), tsp.getId());
  }
}
