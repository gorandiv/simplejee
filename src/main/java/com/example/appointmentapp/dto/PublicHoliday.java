package com.example.appointmentapp.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author goran.divovic
 */
public class PublicHoliday {

    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private List<String> counties;
    private Integer launchYear;
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public List<String> getCounties() {
        return counties;
    }

    public void setCounties(List<String> counties) {
        this.counties = counties;
    }

    public Integer getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Integer launchYear) {
        this.launchYear = launchYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
