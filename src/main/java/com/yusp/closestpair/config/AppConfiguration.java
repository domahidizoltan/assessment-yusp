package com.yusp.closestpair.config;

import com.yusp.closestpair.distance.DistanceCalculator;
import com.yusp.closestpair.distance.EuclideanDistanceCalculator;
import com.yusp.closestpair.io.FilePointsReader;
import com.yusp.closestpair.io.PointsReader;
import com.yusp.closestpair.io.ResultWriter;
import com.yusp.closestpair.io.Writer;
import com.yusp.closestpair.service.ClosestPairPointsAnalyzer;
import com.yusp.closestpair.service.FileInputClosestPairService;
import com.yusp.closestpair.service.PointsAnalyzer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class AppConfiguration {

    @Bean
    public DistanceCalculator distanceCalculator() {
        return new EuclideanDistanceCalculator();
    }

    @Bean
    public ClosestPairPointsAnalyzer closestPairPointsAnalyser(DistanceCalculator distanceCalculator) {
        return new ClosestPairPointsAnalyzer(distanceCalculator);
    }

    @Bean
    public PointsReader pointsReader() {
        return new FilePointsReader();
    }

    @Bean
    public Writer writer() {
        return new ResultWriter();
    }

    @Bean
    public FileInputClosestPairService fileInputClosestPairService(
            PointsAnalyzer pointsAnalyzer,
            PointsReader pointsReader,
            Writer writer,
            ConfigProperties configProperties) {
        return new FileInputClosestPairService(pointsAnalyzer, pointsReader, writer, configProperties);
    }
}
