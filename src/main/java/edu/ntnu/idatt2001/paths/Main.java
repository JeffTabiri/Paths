package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.filehandling.FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Game game;

    static  Scanner scanner = new Scanner(System.in);

    static FileReader fileReader = new FileReader();
    public static void newGame() {
        fileReader.createStory(new File("src/main/resources/filehandletest.paths"));
        game = new Game(new Player("Zac", 100, 0, 0), fileReader.getStory(), new ArrayList<>());
    }

    public static void main(String[] args) {
        newGame();

        Passage currentPassage = game.begin();

        while (currentPassage != null) {
            printPassageInfo(currentPassage);
            printPassageChoiceInfo(currentPassage);
            int choice = scanner.nextInt();
            currentPassage = game.go(currentPassage.getLinks().get(choice - 1));
        }

        System.out.println("Game over");
    }

    public static void printPassageInfo(Passage passage){
        System.out.println(passage.getTitle());
        System.out.println(passage.getContent());

    }

    public static void printPassageChoiceInfo(Passage passage) {
        System.out.println("\nChoose one of the following options:");
        for (int i = 0; i < passage.getLinks().size(); i++) {
            System.out.println(i + 1 + ": " + passage.getLinks().get(i).getText());
        }
    }


}
