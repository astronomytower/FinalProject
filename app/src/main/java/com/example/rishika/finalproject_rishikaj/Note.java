package com.example.rishika.finalproject_rishikaj;

import java.io.Serializable;

/**
 * Created by Rishika on 4/22/17.
 * add date
 * noteTitle and challengeTitle are the same, should i keep the variable names the same too?
 */

public class Note implements Serializable {
    String noteTitle;
    String body;
    String date;

    public Note(){
        this.noteTitle = noteTitle;
        this.body = body;
        this.date = date;
    }

    public Note (String noteTitle, String body, String date){
        this.noteTitle = noteTitle;
        this.body = body;
        this.date = date;
    }

    public String getNoteTitle(){ return noteTitle; }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }


    public void setTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }


//    public String toReadableDate(){
//        long postDate = Long.valueOf(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(postTime);
//        return calendar.getTime().toString();
//    }

    public String toString(){
        return noteTitle +"\n"+ body + "\n" + date;
    }
}
