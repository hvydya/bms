package com.harsha.bookmyshow.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * created on: 06/12/20
 * created by: harsha
 */

// NOT USED. IGNORE THIS FILE

/**
 * Represents the intent of the user to book seats. If succeeds then isSuccess will be true and booking id will be linked.
 * Otherwise isSuccess will be false and booking id will be null.
 */
@Entity
public class BookingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Timestamp requestTs;

    private Boolean isProcessed;

    private Boolean isSuccess;

    @OneToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    public BookingRequest() {
    }

    public BookingRequest(Integer id, Timestamp requestTs) {
        this.id = id;
        this.requestTs = requestTs;
        this.isProcessed = false;
        this.isSuccess = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getRequestTs() {
        return requestTs;
    }

    public void setRequestTs(Timestamp requestTs) {
        this.requestTs = requestTs;
    }

    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
