package com.example.rishika.finalproject_rishikaj;

import java.io.Serializable;

/**
 * Created by Rishika on 5/3/17.
 */

public class WeightsItem implements Serializable {
    String title;
    String body;

    public WeightsItem(){
        this.title = title;
        this.body = body;}

    public WeightsItem (String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getBody () {
        return body;
    }

    public void setBody (String body) {
        this.body = body;
    }

    @Override
    public String toString () {
        return "WeightsItem{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
