package edu.ntnu.idatt2001.paths;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        File file = new File("src/main/resources/filehandletest.paths");

        Filehandler filehandler = new Filehandler();

        filehandler.createStory(file);

        System.out.println(filehandler.getStory().getTitle());

    }

}
