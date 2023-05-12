package com.example.appointmentapp.model;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * @author goran.divovic
 */
public class Appointment {

    @NotNull
    private String id;

    @NotNull
    private String title;

    private Date date;

    private String location;

    private String detail;

    public Appointment() {

    }

    public Appointment(String title, Date date, String location, String detail) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}