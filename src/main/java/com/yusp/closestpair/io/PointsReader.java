package com.yusp.closestpair.io;

import com.yusp.closestpair.model.Point;

import java.io.IOException;
import java.util.List;

public interface PointsReader {

    List<Point> read(String path) throws IOException;

}
