package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.models.BookingRequest;
import com.harsha.bookmyshow.models.ShowTime;

import java.sql.Date;
import java.util.List;

/**
 * created on: 07/12/20
 * created by: harsha
 */
public class BookSeats {

    private Integer screeningId;

    private List<Integer> seats;

    private Date dateOfScreening;

    public BookSeats() {
    }

    public Date getDateOfScreening() {
        return dateOfScreening;
    }

    public void setDateOfScreening(Date dateOfScreening) {
        this.dateOfScreening = dateOfScreening;
    }

    public BookSeats(Integer screeningId, List<Integer> seats) {
        this.screeningId = screeningId;
        this.seats = seats;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
}
