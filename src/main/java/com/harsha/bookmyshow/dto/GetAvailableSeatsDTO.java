package com.harsha.bookmyshow.dto;

import com.harsha.bookmyshow.models.ShowTime;

import java.util.Date;

/**
 * created on: 08/12/20
 * created by: harsha
 */


public class GetAvailableSeatsDTO {

    private Integer screeningId;
    private Date date;
    private ShowTime showTime;

    public GetAvailableSeatsDTO(Integer screeningId, Date date, ShowTime showTime) {
        this.screeningId = screeningId;
        this.date = date;
        this.showTime = showTime;
    }

    public GetAvailableSeatsDTO() {
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }
}
