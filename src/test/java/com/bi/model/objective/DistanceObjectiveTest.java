package com.bi.model.objective;

import com.bi.model.distance.HaversineCalculator;
import com.bi.model.location.Coordinate;
import com.bi.model.location.CoordinateTest;
import com.bi.model.location.Stop;
import com.bi.model.location.StopTest;
import com.bi.model.tsp.TspSolution;
import com.bi.util.DistanceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DistanceObjectiveTest {

  @Mock HaversineCalculator haversineCalculator;
  @InjectMocks DistanceObjective distanceObjective;

  public static DistanceObjective createTestObjective() {
    return new DistanceObjective(new HaversineCalculator());
  }

  @Test
  public void testEvaluate() {

    Coordinate initial = CoordinateTest.create();
    List<Stop> points = StopTest.createList(2);

    double first = DistanceUtils.haversine(initial, points.get(0));
    double middle = DistanceUtils.haversine(points.get(0), points.get(1));
    double last = DistanceUtils.haversine(points.get(1), initial);

    double expected = first + middle + last;

    TspSolution solution = TspSolution.builder().initial(initial).stops(points).build();

    Answer<Double> answer =
        invocation -> {
          Coordinate coordinate1 = invocation.getArgument(0);
          Coordinate coordinate2 = invocation.getArgument(1);
          return DistanceUtils.haversine(coordinate1, coordinate2);
        };

    Mockito.doAnswer(answer)
        .when(haversineCalculator)
        .getDistance(Mockito.any(Coordinate.class), Mockito.any(Coordinate.class));

    double actual = distanceObjective.evaluate(solution);

    assertEquals(expected, actual);
  }
}
