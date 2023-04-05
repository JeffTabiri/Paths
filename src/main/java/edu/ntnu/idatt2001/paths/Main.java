package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.actions.GoldAction;
import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.filehandling.StorySaver;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("src/main/resources/storyExample.paths");
        StoryLoader loadFile = new StoryLoader(file);
        StorySaver saveFile = new StorySaver();


        System.out.println(saveFile.saveStory(loadFile.getStory()));

    }

}
