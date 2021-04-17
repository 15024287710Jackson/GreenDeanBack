package com.example.demo.upLoadFile.action;

public class UpdateResquest {

    String id;
    String number;
    String pictureUrl;
    String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
