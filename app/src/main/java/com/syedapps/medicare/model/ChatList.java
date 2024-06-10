package com.syedapps.medicare.model;

import java.io.Serializable;


public class ChatList implements Serializable
{
    String id;
    String lastmessage;
    int count;
    Long timestamp;
    String personName;
    String personID;

    public ChatList(String lastmessage, int count, Long timestamp, String personName) {
        this.lastmessage = lastmessage;
        this.count = count;
        this.timestamp = timestamp;
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
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

}
