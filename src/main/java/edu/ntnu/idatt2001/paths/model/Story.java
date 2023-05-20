package edu.ntnu.idatt2001.paths.model;

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
    private final Map<Link, Passage> passages;
    private final Passage openingPassage;

    /**
     * The constructor for the class Story.
     *
     * @param title the title of the story. It is a string.
     * @param openingPassage the opening passage of the story. It is a object of the class Passage.
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


    public String getTitle() {
        return title;
    }


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
     */

    public void addPassage(Passage passage) {
        passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
    }

    /**
     * A method for removing a passage from the collection of passages
     * The passage cannot be removed if it has links to it
     *
     * @param link identifies the passage in the collection of passages
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
     * A method for getting a list of broken links
     * A broken link is a link that does not exist in the collection of passages
     *
     * @return a list of broken links
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


