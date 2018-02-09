package com.yusp.closestpair.service;

import com.yusp.closestpair.config.ConfigProperties;
import com.yusp.closestpair.io.Writer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileInputClosestPairITest {

    private final static String INPUT_FORMAT = "sample_input_%s.tsv";
    private final static String OUTPUT_FORMAT = "sample_output_%s.txt";

    @MockBean
    private Writer writerMock;

    @Inject
    private ConfigProperties configProperties;

    @Inject
    private FileInputClosestPairService closestPairService;

    @Test
    public void shouldOutputFileWithLines3And6() throws Exception {
        String scenario = "2_8";
        String inputFile = format(INPUT_FORMAT, scenario);
        ArgumentCaptor<List> resultsCaptor = ArgumentCaptor.forClass(List.class);

        closestPairService.analyzeFile(inputFile);

        String expectedOutputFile = configProperties.getOutputFolder() + format(OUTPUT_FORMAT, scenario);
        verify(writerMock).write(eq(expectedOutputFile), resultsCaptor.capture());
        List<String> expectedOutput = resultsCaptor.getValue();
        assertEquals(2, expectedOutput.size());
        assertEquals("3:742431	-772652", expectedOutput.get(0));
        assertEquals("6:726622	-813088", expectedOutput.get(1));
    }

    private List<String> readLinesOfOutputFile(String outputFile) throws Exception {
        Path path = Paths.get(getClass().getResource("/data/output/" + outputFile).toURI());
        return Files.lines(path).collect(Collectors.toList());
    }
}
