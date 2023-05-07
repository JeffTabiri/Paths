package edu.ntnu.idatt2001.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  A {@code Passage} class is used to bind multiple parts of a story.
 *  A link makes it possible to go from a path to another.
 *  It contains fields such as text, reference and a list of action type objects.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class Passage {
    String title;
    String content;
    List<Link> links = new ArrayList<>();

    private String fileName;

    private String url;

    /**
     * Constructor which is responsible for the creation of the Passage class.
     *
     * @param title describes a text which acts as a form of identification for a passage.
     *              This text comes as a string.
     *
     * @param content describes a string which contains the content of
     *                a passage in form of a paragraph or dialogue.
     *
     * @throws IllegalArgumentException if the title or content is empty.
     */
    public Passage(String title, String content) {

        if (title.isEmpty()) {
            throw new IllegalArgumentException("The title can't be empty.");
        }
        else if (content.isEmpty()) {
            throw new IllegalArgumentException("The content can't be empty.");
        }

        this.title = title;
        this.content = content;
    }


    /**
     * Constructor which is responsible for the creation of the Passage class
     *
     * @param title describes a text which acts as a form of identification for a passage
     *              This text comes as a string
     *
     * @param content describes a string which contains the content of
     *                a passage in form of a paragraph or dialogue
     *
     * @param links is a list of links that are added to the list of links in the passage object
     *
     * @throws IllegalArgumentException if the title or content is empty.
     */
    public Passage(String title, String content, List<Link> links) {

        if (title.isEmpty()) {
            throw new IllegalArgumentException("The title can't be empty.");
        }
        else if (content.isEmpty()) {
            throw new IllegalArgumentException("The content can't be empty.");
        }

        this.title = title;
        this.content = content;
        this.links.addAll(links);
    }


    public Passage(String title, String content, List<Link> links, String fileName) {

        if (title.isEmpty()) {
            throw new IllegalArgumentException("The title can't be empty.");
        }
        else if (content.isEmpty()) {
            throw new IllegalArgumentException("The content can't be empty.");
        }

        this.title = title;
        this.content = content;
        this.links.addAll(links);
        this.fileName = fileName;
        url = "src/main/resources/images/background/" + fileName + ".jpg";
    }



    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getUrl() {
        if(fileName == "" || fileName == null) {
            return "";
        }else {
            return url;
        }
    }

    public String getFileName() {
        return fileName;
    }




    /**
     * A method for adding a link to the list of links.
     *
     * @param link is a link object that is added to the list of links.
     */
    public boolean addLink(Link link) {
        return links.add(link);
    }

    /**
     * A method for checking if the list of links is empty.
     *
     * @return a boolean value which is true if the list of links is empty, otherwise false.
     */
    public boolean hasLinks() {
        return !links.isEmpty();
    }

    /**
     * A method for checking the equality of two instances of the same class.
     *
     * @param o is the object instance we are checking against.
     *
     * @return a boolean value which checks if the two objects are identical
     *         Can be either true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Passage passage = (Passage) o;
        return Objects.equals(title, passage.title)
                && Objects.equals(content, passage.content) && Objects.equals(links, passage.links);
    }

    /**
     * Overridden method which will help the program function with hash-based collections.
     *
     * @return a hashcode, which acts as an identifier for the class, is usually in int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, content, links);
    }


    /**
     * General representation of the class.
     *
     * @return a toString() which will provide a general representation of the class.
     */
    @Override
    public String toString() {
        return title
                +
                "\n"
                +
                content;
    }
}

