package com.bi.model.annealing;

import com.bi.model.tsp.NaiveAnnealingSystem;
import com.bi.model.tsp.SimulatedAnnealingSystem;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum TspParameter {
  MAX_ITERATION_COUNT(SimulatedAnnealingSystem.class),
  SEARCH_STRENGTH(NaiveAnnealingSystem.class),
  STORE_HISTORY(SimulatedAnnealingSystem.class);

  private final List<Class<? extends SimulatedAnnealingSystem>> classes;

  @SafeVarargs
  TspParameter(Class<? extends SimulatedAnnealingSystem>... classes) {
    this.classes = Arrays.stream(classes).collect(Collectors.toList());
  }

  public static Collection<TspParameter> getRequiredParameters(
      Class<? extends SimulatedAnnealingSystem> clazz) {
    return Arrays.stream(TspParameter.values())
        .filter(e -> e.classes.stream().anyMatch(c -> c.isAssignableFrom(clazz)))
        .collect(Collectors.toSet());
  }
}
