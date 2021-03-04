package com.bi.service;

import com.bi.model.annealing.TspParameter;
import com.bi.model.annealing.ParameterMap;
import com.bi.model.distance.HaversineCalculator;
import com.bi.model.objective.DistanceObjective;
import com.bi.model.tsp.TravellingSalesmanProblem;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspProblemTest;
import com.bi.model.tsp.TspSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class HaversineTspServiceTest {

  @Spy HaversineCalculator calculator = new HaversineCalculator();
  @Spy DistanceObjective distanceObjective = new DistanceObjective(calculator);
  @Spy HaversineTspService haversineTspService = new HaversineTspService(distanceObjective);

  @Test
  void testSystemDoesTerminate() {
    TspProblem tspProblem = TspProblemTest.create(50);
    TravellingSalesmanProblem tsp =
        haversineTspService.solve(
            tspProblem,
            ParameterMap.create(
                TspParameter.MAX_ITERATION_COUNT,
                100,
                TspParameter.SEARCH_STRENGTH,
                20,
                TspParameter.STORE_HISTORY,
                true));

    TspSolution defaultSolution = tspProblem.defaultSolution().evaluate(distanceObjective);

    TspSolution solution = tsp.getSolution();

    double score = solution.getScore();
    Assertions.assertTrue(score < defaultSolution.getScore());

    List<TspSolution> history = tsp.getHistory();
    double lastScore = history.get(history.size() - 1).getScore();
    Assertions.assertEquals(lastScore, score);
  }
}
