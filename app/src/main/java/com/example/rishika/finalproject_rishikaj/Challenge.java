package com.example.rishika.finalproject_rishikaj;

import java.io.Serializable;

/**
 * Created by Rishika on 4/22/17.
 * change time to date.
 */

public class Challenge implements Serializable {
    String challengeTitle;
    String body;
    String date;
    String challenge;

    public Challenge(){
        this.challengeTitle = challengeTitle;
        this.body = body;
        this.date = date;
    }

    public Challenge (String challengeTitle, String body, String date){
        this.challengeTitle = challengeTitle;
        this.body = body;
        this.date = date;
    }

    public String getTitle(){
        return challengeTitle;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }


    public void setTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String toString(){
        return challengeTitle +"\n"+ body + "\n" + date;
    }
}
