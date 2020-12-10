package com.harsha.bookmyshow.dto;

import java.sql.Time;
import java.util.Date;

/**
 * created on: 08/12/20
 * created by: harsha
 */


public class GetAvailableSeatsDTO {

    private Integer screeningId;
    private Date date;

    public GetAvailableSeatsDTO(Integer screeningId, Date date) {
        this.screeningId = screeningId;
        this.date = date;
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
}
