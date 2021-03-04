package com.bi.model.annealing;

import java.util.EnumMap;

public class ParameterMap extends EnumMap<AnnealingParameter, Number> {
  public ParameterMap() {
    super(AnnealingParameter.class);
  }

  public static ParameterMap create(Object... entries) {
    ParameterMap parameterMap = new ParameterMap();
    for (int i = 0; i < entries.length; i += 2) {
      parameterMap.put((AnnealingParameter) entries[i], (Number) entries[i + 1]);
    }
    return parameterMap;
  }

  public Double getDouble(AnnealingParameter parameter) {
    Number number = get(parameter);
    if (number == null) return null;
    return number.doubleValue();
  }

  public Integer getInteger(AnnealingParameter parameter) {
    Number number = get(parameter);
    if (number == null) return null;
    return number.intValue();
  }
}
