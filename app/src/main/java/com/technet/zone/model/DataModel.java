package com.technet.zone.model;


public class DataModel {
    private String _id;
    private String title;
    private String image;
    private String writter;
    private String detailnews1;
    private String detailnews2;
    private String detailnews3;
    private String catagory;


    public DataModel(String _id, String title, String image, String writter, String detailnews1, String detailnews2,
                     String detailnews3, String catagory) {
        this._id = _id;
        this.title = title;
        this.image = image;
        this.writter = writter;
        this.detailnews1 = detailnews1;
        this.detailnews2 = detailnews2;
        this.detailnews3 = detailnews3;
        this.catagory = catagory;
    }

    public DataModel(String title, String image, String writter, String detailnews1, String detailnews2,
                     String detailnews3, String catagory) {
        this.title = title;
        this.image = image;
        this.writter = writter;
        this.detailnews1 = detailnews1;
        this.detailnews2 = detailnews2;
        this.detailnews3 = detailnews3;
        this.catagory = catagory;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public String getDetailnews1() {
        return detailnews1;
    }

    public void setDetailnews1(String detailnews1) {
        this.detailnews1 = detailnews1;
    }

    public String getDetailnews2() {
        return detailnews2;
    }

    public void setDetailnews2(String detailnews2) {
        this.detailnews2 = detailnews2;
    }

    public String getDetailnews3() {
        return detailnews3;
    }

    public void setDetailnews3(String detailnews3) {
        this.detailnews3 = detailnews3;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}
