package edu.ntnu.idatt2001.paths.filehandling;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.actions.*;
import edu.ntnu.idatt2001.paths.utility.GameStates;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The class {@code FileReader} reads a file and creates a story.
 * It contains fields objects such as player and story.
 * These fields are used to create a story.
 */
public class StoryLoader {
    private final Story story;


    public StoryLoader(File file) throws IOException {
        String[] passagesInString = splitFileInParagraphs(readFileAsString(file));

        List<Passage> passages = new ArrayList<>();

        for (int i = 0 + 1; i < passagesInString.length ; i++) {
            passages.add(createPassage(passagesInString[i]));
        }

        story = new Story(passagesInString[0], passages.get(0));

        for (Passage passage : passages) {
            story.addPassage(passage);
        }

    }

    public Story getStory() {
        return story;
    }

    /**
     * Gets the title of the passage by removing the :: from the line
     *
     * @param line is the string that is being analyzed
     * @return the title of the passage after the ::
     */
    private String getTitle(String line) {
        return line.substring(2);
    }

    /**
     * Takes the line and returns it as content for the passage
     *
     * @param line is the content of the passage
     * @return the content to the passage in form of a string
     */
    private String getContent(String line) {
        return line;
    }


    /**
     * Takes in a line from the file and returns the text between the parentheses ()
     *
     * @param line is the reference to the link object
     * @return the reference of a link object as a string
     */
    private String getReference(String line) {

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }

    /**
     * Takes in a line from the file and returns the text between the square brackets []
     *
     * @param line is the text to the link object
     * @return the text between the square brackets as a string
     */
    private String getText(String line) {

        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }

    private String getAction(String line) {


        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }

    private String getActionType(String line) {


        Pattern pattern = Pattern.compile("\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }

    private String getActionValue(String line) {

        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group(1) : "";
    }


    /**
     * Reads the entire file and returns it as a string
     *
     * @param file is the document to be read. It is streamed by getting the path of the file
     * @return the entire file as a string
     */
    private String readFileAsString(File file) {

        String text = "";

        try {
            text = new String(Files.readAllBytes(Paths.get(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Splits the file into paragraphs and returns them as an array of strings
     * Splits are made on empty lines
     *
     * @param text is the string to be split
     * @return an array of strings where each string is a paragraph
     */
    private String[] splitFileInParagraphs(String text) {

        String[] result = text.split("\\n\\s*\\n");

        return result;
    }



    private Passage createPassage(String string) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new StringReader(string));
        String currentLine;

        //Fields for passage
        Passage passage = null;
        String passageTitle = "";
        StringBuilder content = new StringBuilder();
        Link currentLink = null;
        List<Link> links = new ArrayList<>();
        String image = "";
        String music = "";


        try {

            while ((currentLine = bufferedReader.readLine()) != null) {

                switch (getLineType(currentLine)) {
                    case "TITLE" -> passageTitle = getTitle(currentLine);

                    case "CONTENT" -> content.append(currentLine);

                    case "LINK" -> {
                        currentLink = new Link(getText(currentLine), getReference(currentLine));
                        links.add(currentLink);
                    }

                    case "ACTION" -> {
                        if (currentLink != null) {
                            currentLink.addAction(createAction(getActionType(currentLine), getActionValue(currentLine)));
                        }
                    }

                    case "IMAGE" -> image = getTitle(currentLine);

                    case "MUSIC" -> music = getTitle(currentLine);

                    default -> throw new IllegalStateException("Unexpected value type.");
                }

            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            bufferedReader.close();
        }

        if(!image.equals("")||!image.equals(null)) {
            passage = new Passage(passageTitle, content.toString(), links, image);
            passage.setGameState(getStateType(music));
        } else {
            passage = new Passage(passageTitle, content.toString(), links);
        }


        return passage;
    }



    /**
     * Takes in a line from a passage (formatted as a string) and returns the type of line.
     *
     * @param line is the string that is being analyzed
     * @return the type of line as a string
     */
    private String getLineType(String line) {

        if (line.startsWith("::")) {
            return "TITLE";

        } else if (line.startsWith("++")) {
            return "IMAGE";

        } else if (line.startsWith("--")) {
            return "MUSIC";

        } else if (!line.startsWith("::") && !line.startsWith("[") && !line.startsWith("{") && !line.startsWith("++")) {
            return "CONTENT";

        } else if (line.startsWith("[")) {
            return "LINK";

        } else if (line.startsWith("{")) {
            return "ACTION";


        } else {
            return "";

        }

    }


    private GameStates getStateType(String line) {

        if (line.equalsIgnoreCase("Cave")) {
            return GameStates.EXPLORE;
        } else if (line.equalsIgnoreCase("Forest")) {
            return GameStates.EXPLORE;
        } else if (line.equalsIgnoreCase("Castle")) {
            return GameStates.EXPLORE;
        } else if (line.equalsIgnoreCase("Town")) {
            return GameStates.EXPLORE;
        } else if (line.equalsIgnoreCase("Dungeon")) {
            return GameStates.ENEMY;
        } else if (line.equalsIgnoreCase("Boss")) {
            return GameStates.BOSS;
        } else if (line.equalsIgnoreCase("Rest")) {
            return GameStates.REST;
        } else if (line.equalsIgnoreCase("Shop")) {
            return GameStates.SHOP;
        } else {
            return GameStates.HELP_MENU;
    }

    }

    /**
     * Takes in a line from an action (formatted as a string) and creates an action object.
     * The type of action is determined by the first word in the line.
     * The value of the action is determined by the number in the parentheses.
     *
     * @param actionDescription is a string which contains the type of action
     * @param actionValue is a string which contains the value of the action
     * @return a new action object of the type specified in the line
     */
    private Action createAction(String actionDescription, String actionValue) {
        
        if (actionDescription.isEmpty()) {
            throw new IllegalArgumentException("Action description cannot be null");
        }
        if (actionValue.isEmpty()) {
            throw new IllegalArgumentException("Action value cannot be null");
        }

        return switch (actionDescription) {
            case "Score" -> new ScoreAction(Integer.parseInt(actionValue));
            case "Health" -> new HealthAction(Integer.parseInt(actionValue));
            case "Inventory" -> new InventoryAction(actionValue);
            case "Gold" -> new GoldAction(Integer.parseInt(actionValue));
            default -> null;
        };
    }

}