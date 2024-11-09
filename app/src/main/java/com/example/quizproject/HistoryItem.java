package com.example.quizproject;

public class HistoryItem {

    private String topic;
    private int score;
    private String date;

    public HistoryItem(String topic, int score, String date) {
        this.topic = topic;
        this.score = score;
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
