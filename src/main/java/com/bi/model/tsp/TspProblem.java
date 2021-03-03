package com.bi.model.tsp;

import com.bi.model.vehicle.Vehicle;
import com.bi.model.location.Stop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TspProblem {
  private Vehicle vehicle;
  private List<Stop> stops;
}
