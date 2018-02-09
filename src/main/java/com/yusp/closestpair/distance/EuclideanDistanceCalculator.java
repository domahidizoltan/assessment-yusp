package com.yusp.closestpair.distance;

import com.yusp.closestpair.model.Point;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.function.ToDoubleFunction;

public class EuclideanDistanceCalculator implements DistanceCalculator {

    public double measure(Point first, Point second) {
        validate(first, second);

        double distanceSum = Seq.zip(first.getCoordinates(), second.getCoordinates())
                .mapToDouble(distanceOfDimension())
                .sum();

        return Math.sqrt(distanceSum);
    }

    private void validate(Point first, Point second) {
        validatePoint(first, "First");
        validatePoint(second, "Second");

        if (first.getCoordinates().size() != second.getCoordinates().size()) {
            throw new IllegalArgumentException("Points must have the same number of dimensions!");
        }
    }

    private void validatePoint(Point first, String messagePrefix) {
        if (null == first || first.getCoordinates().isEmpty()) {
            throw new IllegalArgumentException(messagePrefix + " point must not be null and must have dimensions!");
        }
    }

    private ToDoubleFunction<Tuple2<Double, Double>> distanceOfDimension() {
        return tuple -> {
            Double dist = tuple.v2 - tuple.v1;
            return dist*dist;
        };
    }

}
