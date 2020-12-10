package com.harsha.bookmyshow.models;

import javax.persistence.*;
import java.util.BitSet;
import java.util.Date;

/**
 * created on: 08/12/20
 * created by: harsha
 */

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private BitSet bookedSeats;

    @ManyToOne
    @JoinColumn(name = "screening_id", referencedColumnName = "id", nullable = false)
    private Screening screening;

    @Column(nullable = false)
    private ShowTime showTime;

    @Column(nullable = false)
    private Date screeningDate;

    @Column(nullable = false)
    private Date bookedDate;

    public Ticket() {
    }

    public Ticket(BitSet bookedSeats, Screening screening, ShowTime showTime, Date screeningDate, Date bookedDate) {
        this.bookedSeats = bookedSeats;
        this.screening = screening;
        this.showTime = showTime;
        this.screeningDate = screeningDate;
        this.bookedDate = bookedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BitSet getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(BitSet bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", bookedSeats=" + bookedSeats +
                ", screening=" + screening +
                ", showTime=" + showTime +
                ", screeningDate=" + screeningDate +
                ", bookedDate=" + bookedDate +
                '}';
    }
}
