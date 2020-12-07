package com.harsha.bookmyshow.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.BitSet;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * Screening of the movie in a theatre.
 * Every screening has 3 times. The movie will be played at this screen at all three times.
 * This is to simplify the design for running movies in a theatre. Otherwise would require complex logic to map show
 * timings and track them and prevent overlaps from happening.
 */
@Entity
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    private Date opening;

    private Integer periodOfScreening;

    // One show is only mapped to one screen at any given point of time. TODO Need to block this from schema level. Add validation in logic.
    @ManyToOne
    @JoinColumn(name="screen_id")
    private Screen screen;

    private BitSet seats;

    private ShowTime showTime;

    public Screening(Integer id, Movie movie, Date opening, Integer periodOfScreening, Screen screen, ShowTime showTime) {
        this.id = id;
        this.movie = movie;
        this.opening = opening;
        this.periodOfScreening = periodOfScreening;
        this.screen = screen;
        this.showTime = showTime;
        seats = new BitSet(Screen.NUM_SEATS);
    }

    public BitSet getSeats() {
        return seats;
    }

    public void setSeats(BitSet seats) {
        this.seats = seats;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Screening() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getOpening() {
        return opening;
    }

    public void setOpening(Date opening) {
        this.opening = opening;
    }

    public Integer getPeriodOfScreening() {
        return periodOfScreening;
    }

    public void setPeriodOfScreening(Integer periodOfScreening) {
        this.periodOfScreening = periodOfScreening;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
