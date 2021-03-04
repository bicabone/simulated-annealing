package com.bi.controller;

import com.bi.model.annealing.ParameterMap;
import com.bi.model.annealing.TspParameter;
import com.bi.model.tsp.TravellingSalesmanProblem;
import com.bi.model.tsp.TspProblem;
import com.bi.repository.TspRepository;
import com.bi.service.HaversineTspService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/haversine")
public class HaversineTspController {

  private final HaversineTspService tspService;
  private final TspRepository tspRepository;

  @PostMapping("/solve")
  public String post(@RequestBody TspProblem problem, @RequestParam("history") boolean history) {
    log.info("Incoming tsp: {}", problem);
    ParameterMap parameterMap =
        ParameterMap.builder().put(TspParameter.STORE_HISTORY, history).build();
    TravellingSalesmanProblem solve = tspService.solve(problem, parameterMap);
    return solve.getId();
  }

  @GetMapping("/{id}")
  public TravellingSalesmanProblem get(@PathVariable String id) {
    return tspRepository.get(id);
  }
}
