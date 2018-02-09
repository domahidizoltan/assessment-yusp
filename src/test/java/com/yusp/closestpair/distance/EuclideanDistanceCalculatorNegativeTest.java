package com.yusp.closestpair.distance;

import com.yusp.closestpair.model.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class EuclideanDistanceCalculatorNegativeTest {

    public static final Point ANY_POINT = new Point(1);
    public static final Point EMPTY_POINT = new Point();
    DistanceCalculator distanceCalculator;

    private final Point firstPoint;
    private final Point secondPoint;

    public EuclideanDistanceCalculatorNegativeTest(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[] firstPointIsEmpty = {
            EMPTY_POINT,
            ANY_POINT
        };

        Object[] secondPointIsEmpty = {
            ANY_POINT,
            EMPTY_POINT
        };

        Object[] bothPointsAreEmpty = {
            EMPTY_POINT,
            EMPTY_POINT
        };

        Object[] firstPointIsNull = {
            null,
            ANY_POINT
        };

        Object[] secondPointIsNull = {
            ANY_POINT,
            null
        };

        Object[] bothPointsAreNull = {
            null,
            null
        };

        Object[] pointsHasDifferentDimensions = {
            ANY_POINT,
            new Point(1, 1)
        };


        return Arrays.asList(new Object[][] {
            firstPointIsEmpty,
            secondPointIsEmpty,
            bothPointsAreEmpty,
            firstPointIsNull,
            secondPointIsNull,
            bothPointsAreNull,
            pointsHasDifferentDimensions
        });
    }

    @Before
    public void setUp() {
        distanceCalculator = new EuclideanDistanceCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnDistanceOfNDimensionalPoints() {
        distanceCalculator.measure(firstPoint, secondPoint);
    }

}
