package com.harsha.bookmyshow.dto;

import com.harsha.bookmyshow.models.ShowTime;

import java.sql.Date;
import java.util.List;

/**
 * created on: 07/12/20
 * created by: harsha
 */
public class SeatBookingDTO {

    private Integer screeningId;

    private List<Integer> seats;

    private Date dateOfScreening;

    private ShowTime showTime;

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public SeatBookingDTO() {
    }

    public Date getDateOfScreening() {
        return dateOfScreening;
    }

    public void setDateOfScreening(Date dateOfScreening) {
        this.dateOfScreening = dateOfScreening;
    }

    public SeatBookingDTO(Integer screeningId, List<Integer> seats) {
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

    @Override
    public String toString() {
        return "BookSeats{" +
                "screeningId=" + screeningId +
                ", seats=" + seats +
                ", dateOfScreening=" + dateOfScreening +
                ", showTime=" + showTime +
                '}';
    }
}
