package com.bi.controller;

import com.bi.model.TspProblem;
import com.bi.model.TspSolution;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/solve")
public class TspController {
  @PostMapping
  public TspSolution post(@RequestBody TspProblem body) {
    log.info("Incoming tsp: {}", body);
    return null;
  }
}
