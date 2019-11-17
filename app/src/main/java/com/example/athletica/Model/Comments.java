package com.example.athletica.Model;

public class Comments {

    private String userID;
    private String userName;
    private String commentContent;


    /**
     *
     * @param userID
     * @param userName
     * @param commentContent
     */

    public Comments(String userID, String userName, String commentContent) {
        this.userID = userID;
        this.userName = userName;
        this.commentContent = commentContent;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentContent() {
        return commentContent;
    }

}
