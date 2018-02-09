package com.yusp.closestpair.service;

import com.yusp.closestpair.distance.DistanceCalculationException;
import com.yusp.closestpair.distance.DistanceCalculator;
import com.yusp.closestpair.distance.EuclideanDistanceCalculator;
import com.yusp.closestpair.model.Point;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClosestPairPointsAnalyserTest {

    private PointsAnalyzer analyzer;
    public static final List<Point> ANY_POINTS = Arrays.asList(
        new Point(-262972, 508697),
        new Point(-311943.65362731507, 370239.3559213022),
        new Point(742431, -772652),
        new Point(-346046, 696615.3537438104),
        new Point(194172, 103527),
        new Point(726621.8167057682, -813087.8844925504),
        new Point(167923, -312455.0459619701),
        new Point(499664.42762545496, 72395.09685360803)
    );

    private DistanceCalculator distanceCalculatorSpy;

    @Before
    public void setUp() {
        distanceCalculatorSpy = Mockito.spy(new EuclideanDistanceCalculator());
        analyzer = new ClosestPairPointsAnalyzer(distanceCalculatorSpy);
    }

    @Test
    public void shouldReturnPoints3And6() {
        List<Point> actualPoints = analyzer.analyze(ANY_POINTS);
        assertEquals(2, actualPoints.size());
        assertEquals(ANY_POINTS.get(2), actualPoints.get(0));
        assertEquals(ANY_POINTS.get(5), actualPoints.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGetNullInput() {
        analyzer.analyze(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGetEmptyInput() {
        analyzer.analyze(Lists.emptyList());
    }

    @Test(expected = DistanceCalculationException.class)
    public void shouldThrowExceptionWhenCalculatorThrowsException() {
        willThrow(new IllegalArgumentException())
            .given(distanceCalculatorSpy).measure(any(Point.class), any(Point.class));
        analyzer.analyze(ANY_POINTS);
    }
}
