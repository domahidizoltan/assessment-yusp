package com.yusp.closestpair.service;

import com.yusp.closestpair.distance.DistanceCalculationException;
import com.yusp.closestpair.distance.DistanceCalculator;
import com.yusp.closestpair.distance.PointTuple;
import com.yusp.closestpair.model.Point;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class ClosestPairPointsAnalyzer implements PointsAnalyzer {

    private DistanceCalculator distanceCalculator;

    public ClosestPairPointsAnalyzer(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public List<Point> analyze(List<Point> points) {
        if (CollectionUtils.isEmpty(points)) {
            throw new IllegalArgumentException("Input must not be null or empty!");
        }

        List<PointTuple> pairs = getAllPairs(points);
        try {
            Optional<PointTuple> reduced = pairs.stream().reduce(reduceToClosest());
            PointTuple resultTuple = reduced.get();
            return Arrays.asList(resultTuple.get().v1, resultTuple.get().v2);
        } catch (RuntimeException ex) {
            throw new DistanceCalculationException("Got an error during distance calculation!", ex);
        }
    }

    private List<PointTuple> getAllPairs(List<Point> points) {
        int size = points.size();
        List<PointTuple> result = new ArrayList<>();

        IntStream.range(0, size-1)
            .forEach(i -> {
                Point first = points.get(i);
                points.subList(i+1, size).stream()
                        .map(p -> new PointTuple(first, p))
                        .forEach(result::add);
            });
        return result;
    }

    private BinaryOperator<PointTuple> reduceToClosest() {
        return (t1, t2) -> {
            double d1 = distanceCalculator.measure(t1.get().v1, t1.get().v2);
            double d2 = distanceCalculator.measure(t2.get().v1, t2.get().v2);
            PointTuple result = d1 <= d2 ? t1 : t2;
            return result;
        };
    }

}
