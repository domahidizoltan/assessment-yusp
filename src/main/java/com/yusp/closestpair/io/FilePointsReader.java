package com.yusp.closestpair.io;

import com.yusp.closestpair.model.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePointsReader implements PointsReader {

    public List<Point> read(String path) throws IOException {
        List<Point> points = new ArrayList<>();
        Path file = Paths.get(path);

        Files.lines(file).forEach(line -> {
            String[] lineCoords = line.split("\t");
            List<Double> coords = new ArrayList<>();
            for (int i = 0; i < lineCoords.length ; i++) {
                coords.add(Double.parseDouble(lineCoords[i]));
            }
            points.add(new Point(coords));
        });

        return points;
    }

}
