package edu.ntnu.idatt2001.paths.model.filehandling;

import edu.ntnu.idatt2001.paths.model.Link;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Story;
import edu.ntnu.idatt2001.paths.model.actions.Action;

/**
 *<h1>StorySaver</h1>
 * The class {@code StorySaver} represents a story saver.
 * It contains methods that convert a story to a string.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 06/02/2023
 */
public class StorySaver {


  /**
   * The method {@code saveStory} saves a story to a file.
   *
   * @param story is the story to be saved to a file.
   * @return the story as a string
   */
  public String saveStory(Story story) {

    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }

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
   * The method {@code writePassage} writes the title, content and links of a passage to a file.
   *
   * @param passage is the passage object whose title, content and links are to be written to a file
   * @return the title, content and links of the passage as a string
   * @throws IllegalArgumentException if the passage is null or the passage title is empty
   */
  public String writePassage(Passage passage) {

    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }

    if (passage.getTitle().equals("")) {
      throw new IllegalArgumentException("Passage title cannot be empty");
    }

    StringBuilder passageToBeWritten = new StringBuilder();

    passageToBeWritten.append(writePassageTitle(passage));
    passageToBeWritten.append("\n").append(passage.getContent());

    for (Link link : passage.getLinks()) {
      passageToBeWritten.append("\n").append(writeLinks(link));
    }

    return passageToBeWritten.toString();
  }


  /**
   * The method {@code writeLinks} print the links of a passage to a file.
   *
   * @param link is the link object whose links are to be written to a file
   * @return the links of the passage as a string
   */
  private String writeLinks(Link link) {
    return "[" + link.getText() + "](" + link.getReference() + ")" + writeActions(link);
  }

  /**
   * The method {@code writeActions} prints the actions of a link to the specified file.
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
