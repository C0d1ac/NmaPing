package com.example.andronmaping;

public class History {
    String username,content,timeStamp;

    public History() {
    }

    public History(String username, String timeStamp, String content) {
        this.username = username;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
