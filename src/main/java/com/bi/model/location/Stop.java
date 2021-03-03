package com.bi.model.location;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stop extends Coordinate {
  @Builder.Default private String id = UUID.randomUUID().toString();
}
