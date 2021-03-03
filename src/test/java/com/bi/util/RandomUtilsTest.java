package com.bi.util;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomUtilsTest {

  @Test
  void testGet() {
    int anInt = RandomUtils.getInt(new Random(42), 0, 10);
    assertEquals(7, anInt);
  }

  @Test
  void getIndices() {
    Pair<Integer, Integer> pair = RandomUtils.getPair(10);
    assertTrue(pair.getFirst() < pair.getSecond());
    assertTrue(pair.getFirst() >= 0);
    assertTrue(pair.getSecond() <= 10);
  }
}
