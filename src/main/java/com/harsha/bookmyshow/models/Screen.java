package com.harsha.bookmyshow.models;

import javax.persistence.*;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * Screen (movie hall), where the users watch the movie. A theatre can have multiple screens.
 */
@Entity
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="theatre_id", referencedColumnName = "id", nullable = false)
    private Theatre theatre;

    @Column(nullable = false)
    private Integer numberOfSeats;

    public Screen(String name, Theatre theatre, Integer numberOfSeats) {
        this.name = name;
        this.theatre = theatre;
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Theatre getTheater() {
        return theatre;
    }

    public void setTheater(Theatre theatre) {
        this.theatre = theatre;
    }

    public Screen() {
    }
}
