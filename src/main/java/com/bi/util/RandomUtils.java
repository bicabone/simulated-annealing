package com.bi.util;

import org.springframework.data.util.Pair;

import java.util.Random;

public class RandomUtils {

  private RandomUtils(){}

  public static Pair<Integer, Integer> getPair(int max) {
    int a = getInt(max);
    int b;
    while (true) {
      if ((b = getInt(max)) != a) break;
    }
    return Pair.of(Math.min(a, b), Math.max(a, b));
  }

  public static int getInt(int maximum) {
    return getInt(new Random(), 0, maximum);
  }

  public static int getInt(Random rn, int minimum, int maximum) {
    return minimum + rn.nextInt(maximum - minimum + 1);
  }
}
