package com.yusp.closestpair.service;

import com.yusp.closestpair.config.ConfigProperties;
import com.yusp.closestpair.io.PointsReader;
import com.yusp.closestpair.io.Writer;
import com.yusp.closestpair.model.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class FileInputClosestPairService {

    private static final String LINE_FORMAT = "%d:%s";

    private PointsAnalyzer pointsAnalyzer;
    private PointsReader pointsReader;
    private final Writer resultWriter;
    private ConfigProperties configProperties;

    public FileInputClosestPairService(PointsAnalyzer pointsAnalyzer, PointsReader pointsReader, Writer writer, ConfigProperties configProperties) {
        this.pointsAnalyzer = pointsAnalyzer;
        this.pointsReader = pointsReader;
        this.resultWriter = writer;
        this.configProperties = configProperties;
    }

    public void analyzeFile(String inputFile) throws Exception {
        List<Point> points = readPoints(inputFile);
        List<Point> closesPoints = pointsAnalyzer.analyze(points);
        writeResults(inputFile, points, closesPoints);
    }

    private List<Point> readPoints(String inputFile) throws IOException {
        return pointsReader.read(configProperties.getInputFolder() + inputFile);
    }

    private void writeResults(String outputFile, List<Point> points, List<Point> closesPoints) throws IOException {
        String inputFile = outputFile.
            replace("input","output")
            .replace(".tsv", ".txt");

        List<String> results = new ArrayList<>();
        results.add(formatResult(closesPoints.get(0), points));
        results.add(formatResult(closesPoints.get(1), points));

        resultWriter.write(configProperties.getOutputFolder() + inputFile, results);
    }

    private String formatResult(Point point, List<Point> points) {
        int lineIndex = points.indexOf(point) + 1;
        return format(LINE_FORMAT, lineIndex, printPoint(point));
    }

    private String printPoint(Point point) {
        return point.getCoordinates().stream()
            .map(coord -> String.valueOf(Math.round(coord)))
            .collect(Collectors.joining("\t"));
    }

}
