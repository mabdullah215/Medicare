package com.syedapps.medicare.model;

public class ChatMessage
{
    private String message;
    private String from;
    private String id;
    private int index;
    private Long timestamp;

    public ChatMessage(String message, String from, String id, int index, Long timestamp) {
        this.message = message;
        this.from = from;
        this.id = id;
        this.index = index;
        this.timestamp = timestamp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
