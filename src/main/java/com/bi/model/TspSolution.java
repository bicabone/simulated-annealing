package com.bi.model;

import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TspSolution extends ArrayList<Stop> {
  private Coordinate initial;
}
