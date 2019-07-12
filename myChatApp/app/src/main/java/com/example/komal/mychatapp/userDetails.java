package com.example.komal.mychatapp;

public class userDetails {
    String username;
    String designation;
    String imageUrl;

    userDetails(String name,String desn){
        this.username=name;
        this.designation=desn;
    }

    public String getUsername() {
        return username;
    }

    public String getDesignation() {
        return designation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
