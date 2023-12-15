package com.aichatbot.singles.date.aiask.askai.datingcube;

public class User {
    private String imageUri;
    private String userId;
    private String name;
    private int age;
    private String gender;
    private String location;
    private String about;
    private String status;
    private String bodyType;
    private String sexuality;
    private String searchGender;

    public User() {
        // Default constructor required for Firebase
    }
    public User(String userId, String name, int age, String gender, String location,
                String about, String status, String bodyType, String sexuality, String searchGender, String imageUri) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.about = about;
        this.status = status;
        this.bodyType = bodyType;
        this.sexuality = sexuality;
        this.searchGender = searchGender;
        this.imageUri = imageUri;
    }

    // Getters and setters for all attributes

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getSexuality() {
        return sexuality;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }

    public String getSearchGender() {
        return searchGender;
    }

    public void setSearchGender(String searchGender) {
        this.searchGender = searchGender;
    }
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}

