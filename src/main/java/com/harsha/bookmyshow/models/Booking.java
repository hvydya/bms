package com.harsha.bookmyshow.models;

import javax.persistence.*;
import java.util.Date;
import java.util.BitSet;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * Booking or Ticket (from user perspective)
 */
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date dateOfBooking;

    private Date dateOfScreening;

    @ManyToOne
    @JoinColumn(name="screen_id", referencedColumnName = "id")
    private Screen screen;

    private BitSet bookedSeats;

    private ShowTime showTime;

    public Booking(Integer id, Date dateOfBooking, Date dateOfScreening, Screen screen, BitSet bookedSeats, ShowTime showTime) {
        this.id = id;
        this.dateOfBooking = dateOfBooking;
        this.dateOfScreening = dateOfScreening;
        this.screen = screen;
        this.bookedSeats = bookedSeats;
        this.showTime = showTime;
    }

    public Booking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Date getDateOfScreening() {
        return dateOfScreening;
    }

    public void setDateOfScreening(Date dateOfScreening) {
        this.dateOfScreening = dateOfScreening;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public BitSet getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(BitSet bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }
}
