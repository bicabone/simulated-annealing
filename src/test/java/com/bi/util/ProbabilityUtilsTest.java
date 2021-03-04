package com.bi.util;

import com.bi.model.objective.DistanceObjective;
import com.bi.model.objective.DistanceObjectiveTest;
import com.bi.model.tsp.TspProblem;
import com.bi.model.tsp.TspProblemTest;
import com.bi.model.tsp.TspSolution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ProbabilityUtilsTest {

  @Test
  public void testAnnealingAcceptance() {

    TspProblem tspProblem = TspProblemTest.create();

    TspSolution tspSolution = tspProblem.defaultSolution();
    TspSolution clonedSolution = CopyUtils.clone(tspSolution);
    Collections.shuffle(clonedSolution.getStops(), new Random(42));

    DistanceObjective testObjective = DistanceObjectiveTest.createTestObjective();
    clonedSolution.evaluate(testObjective);

    double probability1 =
        ProbabilityUtils.annealingAcceptanceProbability(
            testObjective, tspSolution, clonedSolution, clonedSolution.getScore());

    double probability2 =
        ProbabilityUtils.annealingAcceptanceProbability(
            testObjective, clonedSolution, tspSolution, clonedSolution.getScore());

    assertEquals(1, probability1);
    assertTrue(0.86 < probability2 && probability2 < 0.87);
  }

  @Test
  public void testExponentialDistribution() {
    Random random = new Random(42);

    List<Double> samples = new ArrayList<>(1000);
    for (int i = 0; i < 1000; i++) {
      Random randomProbability = new Random(random.nextInt());
      double sample = ProbabilityUtils.exponential(randomProbability, 1);
      samples.add(sample);
    }

    double upper50Bound = ProbabilityUtils.inverseExponentialCdf(1, 0.75);
    double lower50Bound = ProbabilityUtils.inverseExponentialCdf(1, 0.25);

    // Expect to be about 500
    long count =
        samples.stream().filter(e -> e < upper50Bound).filter(e -> e > lower50Bound).count();

    assertTrue(450 <= count && count <= 550);
  }

  @Test
  public void testExponentialDistributionRandom() {

    List<Double> samples1 = new ArrayList<>(1000);
    for (int i = 0; i < 1000; i++) {
      double sample = ProbabilityUtils.exponential(1);
      samples1.add(sample);
    }
    List<Double> samples2 = new ArrayList<>(1000);
    for (int i = 0; i < 1000; i++) {
      double sample = ProbabilityUtils.exponential(2);
      samples2.add(sample);
    }

    assertEquals(1000, samples1.size());
    assertTrue(samples1.stream().allMatch(x -> x > 0));

    assertEquals(1000, samples2.size());
    assertTrue(samples2.stream().allMatch(x -> x > 0));

    assertTrue(
        samples1.stream().mapToDouble(e -> e).average().getAsDouble()
            > samples2.stream().mapToDouble(e -> e).average().getAsDouble());
  }
}
