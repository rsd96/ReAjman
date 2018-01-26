package com.rsd96.reajman;

/**
 * Created by arshadfarooq on 1/26/18.
 */

public class Event {

    private int imageID;
    private String eName;

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public int getImageID() {
        return imageID;
    }

    public String geteName() {
        return eName;
    }

    public Event(int imageID, String eName){
        this.setImageID(imageID);
        this.seteName(eName);
    }

}
