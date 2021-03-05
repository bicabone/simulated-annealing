package com.bi.controller;

import com.bi.model.tsp.TravellingSalesmanProblem;
import com.bi.repository.TspRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/tsp")
public class TspController {

  private final TspRepository tspRepository;

  @GetMapping("/{id}")
  public TravellingSalesmanProblem get(@PathVariable String id) {
    log.info("Get ID {}", id);
    return tspRepository.get(id);
  }

  @GetMapping("page/{page}/{size}")
  public List<TravellingSalesmanProblem> get(@PathVariable int page, @PathVariable int size) {
    log.info("Get page {}/{}", page, size);
    return tspRepository.get(page, size);
  }

  @GetMapping
  public List<TravellingSalesmanProblem> get() {
    log.info("Get all");
    return tspRepository.getAll();
  }
}
