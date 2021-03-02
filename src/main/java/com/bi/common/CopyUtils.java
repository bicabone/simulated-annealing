package com.bi.common;

import com.google.gson.Gson;

public class CopyUtils {

  private CopyUtils() {}

  private static final Gson GSON = new Gson();

  @SuppressWarnings("all")
  public static <T> T clone(T t) {
    String json = GSON.toJson(t);
    return (T) GSON.fromJson(json, t.getClass());
  }
}
