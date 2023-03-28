package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.filehandling.FileWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {

    Passage passage = new Passage("test", "test");
    Link link = new Link("test", "test");
    FileWriter fileWriter = new FileWriter();



    @Test
    void printPassage() {
        fileWriter.printPassage(passage);
        passage.addLink(link);
        String newLine = "\n";
        String expectedValue = "::test" + newLine +
                                "test" + newLine +
                                "[test](test)" + newLine;

        assertEquals(expectedValue, fileWriter.printPassage(passage));

    }
}