package com.yusp.closestpair.distance;

import com.yusp.closestpair.model.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EuclideanDistanceCalculatorTest {

    private static final double DELTA = 0.00000000001;
    private static final double ROOT_27 = 5.19615242271;
    private static final double ROOT_2 = 1.4142135623730951;
    private static final double ROOT_3 = 1.73205080757;
    private static final double ROOT_10 = 3.16227766017;

    DistanceCalculator distanceCalculator;

    private final Point firstPoint;
    private final Point secondPoint;
    private final double expectedDistance;

    public EuclideanDistanceCalculatorTest(Point firstPoint, Point secondPoint, double expectedDistance) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.expectedDistance = expectedDistance;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[] distanceOf2DPoints = {
            new Point(1, 1),
            new Point(2, 2),
            ROOT_2
        };

        Object[] distanceOf3DPoints = {
            new Point(1, 1, 1),
            new Point(2, 2, 2),
            ROOT_3
        };

        Object[] distanceOf10DPoints = {
            new Point(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            new Point(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            ROOT_10
        };

        Object[] distanceWhenFirstPointHasNegativeCoordinates = {
            new Point(-1, -1, -1),
            new Point(2, 2, 2),
            ROOT_27
        };

        Object[] distanceWhenSecondPointHasNegativeCoordinates = {
            new Point(1, 1, 1),
            new Point(-2, -2, -2),
            ROOT_27
        };

        Object[] distanceWhenBothPointsHasNegativeCoordinates = {
            new Point(-1, -1, -1),
            new Point(-2, -2, -2),
            ROOT_3
        };

        return Arrays.asList(new Object[][] {
            distanceOf2DPoints,
            distanceOf3DPoints,
            distanceOf10DPoints,
            distanceWhenFirstPointHasNegativeCoordinates,
            distanceWhenSecondPointHasNegativeCoordinates,
            distanceWhenBothPointsHasNegativeCoordinates
        });
    }

    @Before
    public void setUp() {
        distanceCalculator = new EuclideanDistanceCalculator();
    }

    @Test
    public void shouldReturnDistanceOfNDimensionalPoints() {
        double actualDistance = distanceCalculator.measure(firstPoint, secondPoint);
        assertEquals(expectedDistance, actualDistance, DELTA);
    }

}
