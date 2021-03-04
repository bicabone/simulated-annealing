package com.bi.model.tsp;

import com.bi.model.vehicle.Vehicle;
import com.bi.model.location.Stop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TspProblem {

  @Builder.Default private String name = UUID.randomUUID().toString();
  @Nonnull private Vehicle vehicle;
  @NotEmpty private List<Stop> stops;

  public TspSolution defaultSolution() {
    return TspSolution.builder().initial(vehicle.getCoordinate()).stops(stops).build();
  }
}
