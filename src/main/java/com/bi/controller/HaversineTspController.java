package com.bi.controller;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.annealing.TspParameter;
import com.bi.model.tsp.TravellingSalesmanProblem;
import com.bi.model.tsp.TspProblem;
import com.bi.service.HaversineTspService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/haversine")
public class HaversineTspController {

  private final HaversineTspService tspService;

  @PostMapping("/solve")
  public String post(
      @RequestBody TspProblem problem,
      @RequestParam(required = false) Boolean history,
      @RequestParam(required = false) Integer searchStrength,
      @RequestParam(required = false) Integer maxIterationCount) {
    log.info("Incoming tsp: {}", problem);
    ParameterMap parameterMap =
        ParameterMap.builder()
            .put(TspParameter.STORE_HISTORY, history)
            .put(TspParameter.SEARCH_STRENGTH, searchStrength)
            .put(TspParameter.MAX_ITERATION_COUNT, maxIterationCount)
            .build();
    TravellingSalesmanProblem solve = tspService.solve(problem, parameterMap);
    return solve.getId();
  }
}
