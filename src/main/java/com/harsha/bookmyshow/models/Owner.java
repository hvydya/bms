package com.harsha.bookmyshow.models;

import javax.persistence.*;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * Owner owns theatres. Owner controls owned theatres and the movies to be played in those.
 */
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    public Owner() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Owner(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}