package com.bi.model.annealing;

import com.bi.model.tsp.NaiveAnnealingSystem;
import com.bi.model.tsp.SimulatedAnnealingSystem;
import com.bi.model.tsp.TspSolution;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TspParameterTest {

  @Test
  void testGetParams() {
    assertTrue(
        TspParameter.getRequiredParameters(NaiveAnnealingSystem.class)
            .containsAll(
                Arrays.asList(
                    TspParameter.MAX_ITERATION_COUNT,
                    TspParameter.SEARCH_STRENGTH,
                    TspParameter.STORE_HISTORY)));

    Collection<TspParameter> requiredParameters =
        TspParameter.getRequiredParameters(simulatedAnnealingSystem.getClass());
    assertEquals(2, requiredParameters.size());
  }

  static final SimulatedAnnealingSystem simulatedAnnealingSystem =
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
        public TspSolution evolve() {return null;}

        @Override
        public boolean isComplete() {
          return false;
        }

        @Override
        public ParameterMap getParameters() {
          return null;
        }
      };
}
