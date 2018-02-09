package com.yusp.closestpair.distance;

import com.yusp.closestpair.model.Point;

public interface DistanceCalculator {

    double measure(Point first, Point second);

}
