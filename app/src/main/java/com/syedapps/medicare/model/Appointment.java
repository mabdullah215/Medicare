package com.syedapps.medicare.model;

import java.io.Serializable;

public class Appointment implements Serializable
{
    String id;
    Doctor doctor;
    String status;
    String date;
    String time;

    public Appointment(Doctor doctor, String status, String date, String time) {
        this.doctor = doctor;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
