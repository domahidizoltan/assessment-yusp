package com.yusp.closestpair.distance;

import com.yusp.closestpair.model.Point;
import org.jooq.lambda.tuple.Tuple2;

public class PointTuple {

    private Tuple2<Point, Point> tuple;

    public PointTuple(Point first, Point second) {
        this.tuple = new Tuple2<>(first, second);
    }

    public Tuple2<Point, Point> get() {
        return tuple;
    }

    @Override
    public String toString() {
        return "PointTuple{" +
            "tuple=" + tuple +
            '}';
    }
}
