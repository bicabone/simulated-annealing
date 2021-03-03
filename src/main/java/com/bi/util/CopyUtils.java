package com.bi.util;

import com.google.gson.Gson;

public class CopyUtils {

  private CopyUtils() {}

  private static final Gson GSON = new Gson();

  @SuppressWarnings("unchecked")
  public static <T> T clone(T t) {
    String json = GSON.toJson(t);
    return (T) GSON.fromJson(json, t.getClass());
  }
}
