package com.example.rishika.finalproject_rishikaj;

import java.io.Serializable;

/**
 * Created by Rishika on 4/30/17.
 */

public class Profile implements Serializable {
    String name;
    String relationship;
    int age;
    String gender;
    String stageOfRecovery;


    public Profile (String name, String relationship, int age, String gender, String stageOfRecovery) {
        this.name = name;
        this.relationship = relationship;
        this.age = age;
        this.gender = gender;
        this.stageOfRecovery = stageOfRecovery;
    }


    public Profile () {
        this.name = name;
        this.relationship = relationship;
        this.age = age;
        this.gender = gender;
        this.stageOfRecovery = stageOfRecovery;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getRelationship () {
        return relationship;
    }

    public void setRelationship (String relationship) {
        this.relationship = relationship;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        this.age = age;
    }

    public String getGender () {
        return gender;
    }

    public void setGender (String gender) {
        this.gender = gender;
    }

    public String getStateOfRecovery () {
        return stageOfRecovery;
    }

    public void setStateOfRecovery (String stateOfRecovery) {
        this.stageOfRecovery = stateOfRecovery;
    }

    @Override
    public String toString () {
        return "Profile" + "\n" +
                "name=" + name + "\n" +
                "relationship=" + relationship + "\n" +
                "age=" + age + "\n" +
                "gender='" + gender + "\n" +
                "stateOfRecovery='" + stageOfRecovery + "\n"
                ;
    }

}
