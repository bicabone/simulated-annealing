package com.bi.model.annealing;

import com.bi.model.tsp.NaiveAnnealingSystem;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import com.bi.model.tsp.TspSolution;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnnealingParameterTest {

  @Test
  void testGetParams() {
    assertTrue(
        AnnealingParameter.getRequiredParameters(NaiveAnnealingSystem.class)
            .containsAll(
                Arrays.asList(
                    AnnealingParameter.MAX_ITERATION_COUNT, AnnealingParameter.SEARCH_STRENGTH)));

    SimulatedAnnealingSystem simulatedAnnealingSystem =
        new SimulatedAnnealingSystem() {
          @Override
          public double getTemperature() {
            return 0;
          }

          @Override
          public TspSolution getSolution() {
            return null;
          }

          @Override
          public void evolve() {}

          @Override
          public boolean isComplete() {
            return false;
          }

          @Override
          public ParameterMap getParameters() {
            return null;
          }
        };

    Collection<AnnealingParameter> requiredParameters =
        AnnealingParameter.getRequiredParameters(simulatedAnnealingSystem.getClass());
    assertEquals(1, requiredParameters.size());
  }
}
