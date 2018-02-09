package com.yusp.closestpair.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point {

    private List<Double> coordinates = new ArrayList<>();

    public Point(double... coordinates) {
        List<Double> coords = Arrays.stream(coordinates).boxed().collect(Collectors.toList());
        this.coordinates.addAll(coords);
    }

    public Point(List<Double> coordinates) {
        this.coordinates.addAll(coordinates);
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Point{" +
            "coordinates=" + coordinates +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(coordinates, point.coordinates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coordinates);
    }

}
