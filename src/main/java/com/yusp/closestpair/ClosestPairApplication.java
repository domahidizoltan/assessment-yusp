package com.yusp.closestpair;

import com.yusp.closestpair.config.ConfigProperties;
import com.yusp.closestpair.service.FileInputClosestPairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ClosestPairApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ClosestPairApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ClosestPairApplication.class, args);

		FileInputClosestPairService closestPairService = ctx.getBean(FileInputClosestPairService.class);
		ConfigProperties config = ctx.getBean("configProperties", ConfigProperties.class);

		analyzeFilesInFolder(closestPairService, config);
	}

	private static void analyzeFilesInFolder(FileInputClosestPairService closestPairService, ConfigProperties config) {
		try {
			Files.list(Paths.get(config.getInputFolder())).forEach(file -> {
				analyzeFile(closestPairService, file);
			});
		} catch (IOException e) {
			LOG.error("Could not walk folder!", e);
		}
	}

	private static void analyzeFile(FileInputClosestPairService closestPairService, Path file) {
		try {
            closestPairService.analyzeFile(file.getFileName().toString());
        } catch (Exception e) {
            LOG.error("Could not analyze points!", e);
        }
	}
}
