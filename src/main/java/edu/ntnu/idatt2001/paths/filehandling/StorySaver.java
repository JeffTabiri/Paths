package edu.ntnu.idatt2001.paths.filehandling;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.actions.Action;

/**
 * The class {@code StorySaver} represents a story saver.
 * It contains methods that saves a story to a file.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class StorySaver {

    public String saveStory(Story story) {

        StringBuilder storyToBeWritten = new StringBuilder();

        storyToBeWritten.append(story.getTitle());

        for (Passage passage : story.getPassages()) {
            storyToBeWritten.append("\n").append("\n").append(writePassage(passage));
        }

        return storyToBeWritten.toString();
    }


    /**
     * The method {@code writePassageTitle} writes the title of a passage to a file.
     *
     * @param passage the passage whose title is to be written to a file
     * @return only the title of the passage as a string.
     */
    private String writePassageTitle(Passage passage) {
        return "::" + passage.getTitle();
    }

    /**
     * The method {@code writePassageContent} writes the content of a passage to a file'
     *
     * @param passage the passage whose content is to be written to a file
     * @return only the content of the passage as a string
     */
    private String writePassageContent(Passage passage) {
        return passage.getContent();
    }


    /**
     * The method {@code writePassage} writes the title, content and links of a passage to a file
     *
     * @param passage is the passage object whose title, content and links are to be written to a file
     * @return the title, content and links of the passage as a string
     */
    public String writePassage(Passage passage) {

        StringBuilder passageToBeWritten = new StringBuilder();

        passageToBeWritten.append(writePassageTitle(passage));
        passageToBeWritten.append("\n").append(passage.getContent());

        for (Link link : passage.getLinks()) {
            passageToBeWritten.append("\n").append(writeLinks(link));
        }

        return passageToBeWritten.toString();
    }

    /**
     * The method {@code writeLinks} writes the links of a passage to a file
     *
     * @param link is the link object whose links are to be written to a file
     * @return the links of the passage as a string
     */
    private String writeLinks(Link link) {

        StringBuilder linkToBeWritten = new StringBuilder();

        linkToBeWritten.append("[").append(link.getText()).append("](").append(link.getReference()).append(")");
        linkToBeWritten.append(writeActions(link));

        return linkToBeWritten.toString();
    }

    /**
     * The method {@code writeActions} writes the actions of a link to a file
     *
     * @param link is the link object whose actions are to be written to a file
     * @return the actions of the link as a string
     */
    private String writeActions(Link link) {

        StringBuilder actions = new StringBuilder();

        for (Action action : link.getActions()) {
            actions.append("\n").append(action.toString());
        }

        return actions.toString();
    }


}
