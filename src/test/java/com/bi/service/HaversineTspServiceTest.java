package com.bi.service;

import com.bi.model.annealing.AnnealingParameter;
import com.bi.model.annealing.ParameterMap;
import com.bi.model.distance.HaversineCalculator;
import com.bi.model.objective.DistanceObjective;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspProblemTest;
import com.bi.model.tsp.TspSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HaversineTspServiceTest {

  @Spy HaversineCalculator calculator = new HaversineCalculator();
  @Spy DistanceObjective distanceObjective = new DistanceObjective(calculator);
  @Spy HaversineTspService haversineTspService = new HaversineTspService(distanceObjective);

  @Test
  void testSystemDoesTerminate() {
    TspProblem tspProblem = TspProblemTest.create();
    TspSolution solution =
        haversineTspService.solve(
            tspProblem,
            ParameterMap.create(
                AnnealingParameter.MAX_ITERATION_COUNT, 100,
                AnnealingParameter.SEARCH_STRENGTH, 20));

    TspSolution defaultSolution = tspProblem.defaultSolution().evaluate(distanceObjective);

    Assertions.assertTrue(
        distanceObjective.evaluate(defaultSolution) > distanceObjective.evaluate(solution));
  }
}
