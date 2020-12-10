package com.harsha.bookmyshow.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

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
    @JoinColumn(name="movie_id", nullable = false)
    private Movie movie;

    @Column(nullable = false)
    private Date opening;

    /**
     * The number of days the movie will be screened.
     */
    @Column(nullable = false)
    private Integer periodOfScreening;

    // One show is only mapped to one screen at any given point of time. TODO Need to block this from schema level. Add validation in logic.
    @ManyToOne
    @JoinColumn(name="screen_id", nullable = false)
    private Screen screen;

    private Date endOfScreening;

    public Screening() {
    }

    public Screening(Movie movie, Date opening, Integer periodOfScreening, Screen screen) {
        this.movie = movie;
        this.opening = opening;
        this.periodOfScreening = periodOfScreening;
        this.screen = screen;
        Calendar c = Calendar.getInstance();
        c.setTime(opening);
        c.add(Calendar.DAY_OF_MONTH, periodOfScreening);
        this.endOfScreening = c.getTime();
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

    public Date getEndOfScreening() {
        return endOfScreening;
    }

    public void setEndOfScreening(Date endOfScreening) {
        this.endOfScreening = endOfScreening;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movie=" + movie +
                ", opening=" + opening +
                ", periodOfScreening=" + periodOfScreening +
                ", screen=" + screen +
                ", endOfScreening=" + endOfScreening +
                '}';
    }
}
