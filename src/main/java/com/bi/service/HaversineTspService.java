package com.bi.service;

import com.bi.model.annealing.AnnealingParameter;
import com.bi.model.annealing.ParameterMap;
import com.bi.model.objective.DistanceObjective;
import com.bi.model.objective.ObjectiveFunction;
import com.bi.model.tsp.NaiveAnnealingSystem;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bi.model.annealing.AnnealingParameter.*;

@Service
public class HaversineTspService extends TspService {

  @Autowired
  public HaversineTspService(DistanceObjective objectiveFunction) {
    super(objectiveFunction);
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
}
