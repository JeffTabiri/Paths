package edu.ntnu.idatt2001.paths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filehandler {

    Story story;

    List<Passage> passages = new ArrayList<>();

    public Story getStory() {
        return story;
    }


    public void createStory(File file) {
        story = new Story(readFromFileAndCreateStory(file), passages.get(0));
        passages.forEach(passage -> story.addPassage(passage));
    }

    public List<Passage> getPassages() {
        return passages;
    }



    public String readFromFileAndCreateStory(File file) {

        String currentLine;

        String storyTitle = "";

        String passageTitle = "";

        StringBuilder content = new StringBuilder();

        List<Link> currentlinks = new ArrayList<>();
        Passage currentPassage = null;
        Link currentLink = null;



        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            storyTitle = bufferedReader.readLine();

            while ((currentLine = bufferedReader.readLine()) != null) {

                switch (getLineType(currentLine)) {
                    case "TITLE" -> passageTitle = findTitle(currentLine);
                    case "CONTENT" -> content.append(findContent(currentLine));
                    case "LINK" -> currentlinks.add(new Link(findText(currentLine), findReference(currentLine)));

                    case "NEW PASSAGE" -> {

                        if (!currentlinks.isEmpty()) {
                            currentPassage = new Passage(passageTitle, content.toString());

                            for (Link currentlink : currentlinks) {
                                currentPassage.addLink(currentlink);
                            }

                            passages.add(currentPassage);

                            currentlinks.clear();
                            currentPassage = null;
                            passageTitle = "";
                            content = new StringBuilder();
                        }
                    }
                    default -> System.out.println("Error");
                }
            }

            currentPassage = new Passage(passageTitle, content.toString());
            for (Link currentlink : currentlinks) {
                currentPassage.addLink(currentlink);
            }

            passages.add(currentPassage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return storyTitle;
    }


    /**
     * Takes in a line and returns the type of line it is
     *
     * @param line a line from the file to analyze
     * @return the type of line
     */
    private String getLineType(String line) {

        if (line.startsWith("::")) {
            return "TITLE";
        }
        else if (!line.startsWith("::") && !line.startsWith("[") && !line.isEmpty()) {
            return "CONTENT";
        }
        else if (line.startsWith("[")) {
            return "LINK";
        }
        else return "NEW PASSAGE";
    }


    /**
     * Finds the title of the passage
     *
     * @param line a line from the file to analyze
     * @return the title of the passag after the ::
     */
    private String findTitle(String line) {
        return line.substring(2);
    }


    /**
     * Takes the line and assigns it to the content variable
     *
     * @param line a line from the file to analyze
     * @return the line
     */
    private String findContent(String line) {
        return line;
    }


    /**
     * Takes in a line from the file and returns the text between the parentheses ()
     *
     * @param line a line from the file to analyze
     * @return the text between the parentheses
     */
    private String findReference(String line) {

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }


    /**
     * Takes in a line from thefile and returns the text between the square brackets []
     *
     * @param line a line from the file to analyze
     * @return the text between the square brackets
     */
    private String findText(String line) {

        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }
}
