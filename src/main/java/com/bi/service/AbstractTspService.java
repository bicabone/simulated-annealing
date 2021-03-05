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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

  public TravellingSalesmanProblem solve(TspProblem problem, ParameterMap parameterMap) {
    TravellingSalesmanProblem tsp = createTsp(problem, parameterMap);
    execute(tsp);
    return tsp;
  }

  TravellingSalesmanProblem createTsp(TspProblem problem, ParameterMap parameterMap) {
    getDefaultParameters().forEach(parameterMap::putIfAbsent);
    TravellingSalesmanProblem tsp =
        TravellingSalesmanProblem.builder()
            .name(problem.getName())
            .parameterMap(parameterMap)
            .problem(problem)
            .build();
    return repository.save(tsp);
  }

  Future<TravellingSalesmanProblem> execute(TravellingSalesmanProblem tsp) {
    return EXECUTOR.submit(() -> solve(tsp.getProblem(), tsp.getParameterMap(), tsp));
  }

  protected TravellingSalesmanProblem solve(
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
    tsp = store(tsp, simulatedAnnealingSystem);

    log.info("Finished solving TSP: {}, id: {}", tsp.getName(), tsp.getId());

    return tsp;
  }

  private TravellingSalesmanProblem store(
      TravellingSalesmanProblem tsp, SimulatedAnnealingSystem simulatedAnnealingSystem) {
    TspSolution solution = simulatedAnnealingSystem.getSolution();
    tsp.setSolution(solution);
    tsp.setComplete(true);
    tsp = repository.save(tsp);
    return tsp;
  }
}
