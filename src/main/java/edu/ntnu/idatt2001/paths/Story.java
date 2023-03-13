package edu.ntnu.idatt2001.paths;

import java.util.*;

/**
 * The class Story represents a story in the game Paths.
 * It contains a title, a collection of passages and an opening passage.
 * The passages makes up the story and the opening passage is the first passage in the story.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class Story {

    private final String title;
    private Map<Link, Passage> passages;
    private Passage openingPassage;

    /**
     * The constructor for the class Story.
     *
     * @param title          the title of the story. It is a string.
     * @param openingPassage the opening passage of the story. It is a object of the class Passage.
     */
    public Story(String title, Passage openingPassage) {
        this.title = title;
        this.openingPassage = openingPassage;
    }

    /**
     * A method for getting the title of the story.
     *
     * @return the title of the story. It is a string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * A method for getting the opening passage of the story.
     *
     * @return the opening passage of the story. It is a object of the class Passage.
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
        return passages.get(link);
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
     */

    public void addPassage(Passage passage) {
        passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
    }

    public void removePassage(Link link) {
        for (Passage passage : passages.values()) {
            for (Link passageLink : passage.getLinks()) {
                if (passageLink.equals(link)) {
                    throw new IllegalArgumentException("Cannot remove passage with links to it.");
                }
            }
        }
        passages.remove(link);

    }

    public List<Link> getBrokenLinks() {
        List<Link> brokenLinks = new ArrayList<>();

        for (Passage passage : passages.values()) {
            for (Link link : passage.getLinks()) {
                if (!passages.containsKey(link)) {
                    brokenLinks.add(link);
                }
            }
        }
        return brokenLinks;
    }

}


