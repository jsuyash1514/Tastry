package com.example.suyash.tastry;

/**
 * Created by Suyash on 28-12-2017.
 */

public class StudentResultModel {
    public String vote;
    public  StudentResultModel(){

    }

    public StudentResultModel(String vote) {
        this.vote = vote;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
