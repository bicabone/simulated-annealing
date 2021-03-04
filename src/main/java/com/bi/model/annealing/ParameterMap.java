package com.bi.model.annealing;

import lombok.NoArgsConstructor;

import java.util.EnumMap;

public class ParameterMap extends EnumMap<TspParameter, Object> {
  public ParameterMap() {
    super(TspParameter.class);
  }

  public static Builder builder() {
    return new ParameterMap.Builder();
  }

  @NoArgsConstructor
  public static class Builder {
    private final ParameterMap parameterMap = new ParameterMap();

    public Builder put(TspParameter parameter, Object value) {
      parameterMap.put(parameter, value);
      return this;
    }

    public ParameterMap build() {
      return parameterMap;
    }
  }

  public static ParameterMap create(Object... entries) {
    Builder builder = ParameterMap.builder();
    for (int i = 0; i < entries.length; i += 2) {
      builder.put((TspParameter) entries[i], entries[i + 1]);
    }
    return builder.build();
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
