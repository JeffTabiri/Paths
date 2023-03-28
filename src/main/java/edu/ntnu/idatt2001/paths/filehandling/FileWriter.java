package edu.ntnu.idatt2001.paths.filehandling;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;

import java.util.List;

public class FileWriter {


    public String printPassage(Passage passage) {
        StringBuilder passageToBeWritten = new StringBuilder();
        passageToBeWritten.append("::" + passage.getTitle()).append("\n");
        passageToBeWritten.append(passage.getContent()).append("\n");
        for (Link link : passage.getLinks()) {
            passageToBeWritten.append("[" + link.getText() + "](" + link.getReference() + ")").append("\n");
        }
        return passageToBeWritten.toString();
    }

    public String  createWholeStory(Story story) {

        story.getOpeningPassage().getLinks().get(0);

        return null;
    }


}
