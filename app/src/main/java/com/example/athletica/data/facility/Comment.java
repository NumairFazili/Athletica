package com.example.athletica.data.facility;

/**
 * This class implements the Comment entity
 * with the attributes userID, commentContent.
 *
 * @author
 * @version 1.0
 * @since
 */
public class Comment {

    private String userID;
    //private String facilityID;
    private String commentContent;

    /**
     * Instantiates a new Comment.
     */
    public Comment() {

    }

    /**
     * Instantiates a new Comment.
     *
     * @param userID         the user id
     * @param commentContent the comment content
     */
    public Comment(String userID, String commentContent) {
        this.userID = userID;
        // this.facilityID=facilityID;
        this.commentContent = commentContent;
    }

    /**
     * Returns a string containing the user id of the user that gave the comment/
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Returns a string containing the content of the comment.
     */
    public String getCommentContent() {
        return commentContent;
    }
}
