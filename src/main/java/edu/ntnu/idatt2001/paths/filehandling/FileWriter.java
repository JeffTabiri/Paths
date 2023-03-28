package edu.ntnu.idatt2001.paths.filehandling;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;

public class FileWriter {


    private String convertPassageToString(Passage passage) {

        StringBuilder passageToBeWritten = new StringBuilder();

        passageToBeWritten.append("::").append(passage.getTitle());
        passageToBeWritten.append("\n").append(passage.getContent());

        for (Link link : passage.getLinks()) {
            passageToBeWritten.append("\n").append("[" + link.getText() + "](" + link.getReference() + ")");
        }

        return passageToBeWritten.toString();
    }

    public String convertStoryIntoString(Story story) {

        StringBuilder storyToBeWritten = new StringBuilder();

        storyToBeWritten.append(story.getTitle());

        for (Passage passage : story.getPassages()) {
            storyToBeWritten.append("\n").append("\n").append(convertPassageToString(passage));
        }

        return storyToBeWritten.toString();
    }

}
