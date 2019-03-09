package com.example.komal.mychatapp;

public class portalDetails {

    String userName;
    String Tag;
    String Question;
    String text;

    portalDetails(String user,String tag,String question){
        this.userName=user;
        this.Tag=tag;
        this.Question=question;
        this.text="Want to Connect?";
    }

    public String getQuestion() {
        return Question;
    }

    public String getUserName() {
        return userName;
    }

    public String getTag() {
        return Tag;
    }

    public String getText() {
        return text;
    }
}
