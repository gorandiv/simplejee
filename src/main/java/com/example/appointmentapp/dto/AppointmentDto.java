package com.example.appointmentapp.dto;

/**
 * @author goran.divovic
 */
public class AppointmentDto {

    private String publicHolidayName;
    private String title;
    private String date;
    private String detail;

    public AppointmentDto(String publicHolidayName, String title, String date, String detail) {
        this.publicHolidayName = publicHolidayName;
        this.title = title;
        this.date = date;
        this.detail = detail;
    }

    public String getPublicHolidayName() {
        return publicHolidayName;
    }

    public void setPublicHolidayName(String publicHolidayName) {
        this.publicHolidayName = publicHolidayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
