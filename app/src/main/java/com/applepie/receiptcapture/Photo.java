package com.applepie.receiptcapture;

/**
 * Created by HPENVY on 13/04/2017.
 */



public class Photo {
    private int id;
    private String name;
    private byte[] image;

    public Photo(String name, byte[] image, int id){
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
