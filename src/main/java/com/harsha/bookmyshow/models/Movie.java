package com.harsha.bookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * The movie that will be shown in the theatres.
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

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

    public Movie(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Movie() {
    }
}
