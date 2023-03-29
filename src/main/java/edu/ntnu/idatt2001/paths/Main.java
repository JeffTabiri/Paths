package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.filehandling.FileReader;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        File file = new File("src/main/resources/filehandletest.paths");

        FileReader fileReader = new FileReader();



        fileReader.createStory(file);

        System.out.println(fileReader.getStory().getTitle());

    }

}
