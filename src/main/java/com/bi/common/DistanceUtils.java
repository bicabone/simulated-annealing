package com.bi.common;

import com.bi.model.Coordinate;

import static java.lang.Math.*;

public class DistanceUtils {

  private static final double RADIUS_OF_EARTH_METERS = 6_378_127d;

  public static double haversine(Coordinate origin, Coordinate destination) {

    if (origin.equals(destination)) return 0d;

    double originLatRads = toRadians(origin.getLatitude());
    double originLonRads = toRadians(origin.getLongitude());
    double destLatRads = toRadians(destination.getLatitude());
    double destLonRads = toRadians(destination.getLongitude());

    double a =
        hav(originLatRads, destLatRads)
            + cos(originLatRads) * cos(destLatRads) * hav(originLonRads, destLonRads);

    return 2d * RADIUS_OF_EARTH_METERS * asin(Math.sqrt(a));
  }

  private static double hav(double a, double b) {
    return .5d - .5d * cos(b - a);
  }
}
