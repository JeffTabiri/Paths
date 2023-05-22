package edu.ntnu.idatt2001.paths.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Story</h1>
 * The class Story represents a story in the game Paths.
 * It contains a title, a collection of passages and an opening passage.
 * The passages makes up the story and the opening passage is the first passage in the story.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since 1.0
 * @see Passage
 * @see Link
 */
public class Story {
  private final String title;
  private final Map<Link, Passage> passages;
  private final Passage openingPassage;

  /**
   * The constructor for the class Story.
   *
   * @param title the title of the story. It is a string.
   * @param openingPassage the opening passage of the story. It is an object of the class Passage.
   * @throws IllegalArgumentException if the title is empty.
   */
  public Story(String title, Passage openingPassage) {

    if (title.isEmpty()) {
      throw new IllegalArgumentException("The title can't be empty.");
    }

    if (openingPassage == null) {
      throw new IllegalArgumentException("The opening passage can't be null.");
    }

    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();

    addPassage(openingPassage);
  }


  /**
   * A method for getting the title of the story.
   *
   * @return the title of the story.
   */
  public String getTitle() {
    return title;
  }


  /**
   * A method for getting the opening passage of the story.
   *
   * @return the opening passage of the story.
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * A method for getting a passage from the collection of passages.
   *
   * @param link identifies the passage in the collection of passages.
   * @return a passage object.
   */
  public Passage getPassage(Link link) {
    return passages.keySet()
            .stream()
            .filter(l -> l.equals(link))
            .map(passages::get)
            .findFirst()
            .orElse(null);
  }


  /**
   * A method for getting the collection of passages.
   *
   * @return a collection of passages.
   */
  public Collection<Passage> getPassages() {
    return passages.values();
  }


  /**
   * A method for adding a passage to the collection of passages.
   * The link is created from the title of the passage.
   *
   * @param passage is a passage object that is added to the collection of passages.
   * @throws IllegalArgumentException if the passage is null.
   */

  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("The passage can't be null.");
    }

    passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
  }

  /**
   * A method for removing a passage from the collection of passages.
   * The passage cannot be removed if it has links to it.
   *
   * @param link identifies the passage in the collection of passage.
   *             
   * @throws IllegalArgumentException if the passage does not exist of
   *                                  if the passage has links to it.
   */
  public void removePassage(Link link) {

    if (!passages.containsKey(link)) {
      throw new IllegalArgumentException("The passage does not exist.");
    }

    Passage tempPassage = passages.get(link);

    boolean hasLinks = passages.values()
            .stream()
            .flatMap(p -> p.getLinks().stream())
            .anyMatch(l -> passages.get(l).equals(tempPassage));

    if (hasLinks) {
      throw new IllegalArgumentException("The passage has links to it.");
    } else {
      passages.remove(link);
    }
  }

  /**
   * A method for getting a list of broken links.
   * A broken link is a link that does not exist in the collection of passages.
   *
   * @return a list of broken links.
   */
  public List<Link> getBrokenLinks() {
    List<Link> brokenLinks = new ArrayList<>();

    for (Passage value : passages.values()) {
      for (Link link : value.getLinks()) {
        if (!passages.containsKey(link)) {
          brokenLinks.add(link);
        }
      }
    }

    return brokenLinks;
  }

}


