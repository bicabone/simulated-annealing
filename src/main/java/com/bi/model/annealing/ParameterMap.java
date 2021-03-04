package com.bi.model.annealing;

import java.util.EnumMap;

public class ParameterMap extends EnumMap<TspParameter, Object> {
  public ParameterMap() {
    super(TspParameter.class);
  }

  public static ParameterMap create(Object... entries) {
    ParameterMap parameterMap = new ParameterMap();
    for (int i = 0; i < entries.length; i += 2) {
      parameterMap.put((TspParameter) entries[i], entries[i + 1]);
    }
    return parameterMap;
  }

  public Double getDouble(TspParameter parameter) {
    Number number = getNumber(parameter);
    if (number == null) return null;
    return number.doubleValue();
  }

  public Integer getInteger(TspParameter parameter) {
    Number number = getNumber(parameter);
    if (number == null) return null;
    return number.intValue();
  }

  private Number getNumber(TspParameter parameter) {
    Object o = get(parameter);
    if (o == null) return null;
    if (!(o instanceof Number)) throw new IllegalStateException();
    return (Number) o;
  }

  public boolean getBoolean(TspParameter parameter) {
    Object o = get(parameter);
    if (o == null) return false;
    if (!(o instanceof Boolean)) throw new IllegalStateException();
    return (Boolean) o;
  }
}
