package com.yusp.closestpair.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ResultWriter implements Writer {

    private static final Logger LOG = LoggerFactory.getLogger(ResultWriter.class);

    public void write(String path, List<String> results) throws IOException {
        Path file = Paths.get(path);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }

        LOG.debug("Writing results to " + path);
        Files.write(file, results, StandardCharsets.UTF_8);
        LOG.debug("\n{}\n{}\n", results.get(0), results.get(1));
    }

}
