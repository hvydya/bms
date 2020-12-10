package com.harsha.bookmyshow.models;

import javax.persistence.*;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * Screen (movie hall), where the users watch the movie. A theatre can have multiple screens. Each screen has 50 seats.
 */
@Entity
public class Screen {

    public static final Integer NUM_SEATS = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="theatre_id", referencedColumnName = "id", nullable = false)
    private Theatre theatre;

    public Screen(String name, Theatre theatre) {
        this.name = name;
        this.theatre = theatre;
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

    public Screen(Integer id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
    }

    public Screen() {
    }
}
