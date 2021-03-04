package com.bi.service;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.objective.DistanceObjective;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.model.tsp.NaiveAnnealingSystem;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import com.bi.model.tsp.TspProblem;
import com.bi.repository.TspRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bi.model.annealing.TspParameter.*;

@Service
public class HaversineTspService extends AbstractTspService {

  @Autowired
  public HaversineTspService(DistanceObjective objectiveFunction, TspRepository tspRepository) {
    super(objectiveFunction, tspRepository);
  }

  @Override
  protected SimulatedAnnealingSystem createSystem(
      TspProblem problem, ObjectiveFunction objectiveFunction, ParameterMap parameterMap) {
    return new NaiveAnnealingSystem(
        problem,
        objectiveFunction,
        parameterMap.getInteger(SEARCH_STRENGTH),
        parameterMap.getInteger(MAX_ITERATION_COUNT));
  }

  @Override
  ParameterMap getDefaultParameters() {
    return ParameterMap.builder()
        .put(SEARCH_STRENGTH, 100)
        .put(MAX_ITERATION_COUNT, 1000)
        .put(STORE_HISTORY, false)
        .build();
  }
}
