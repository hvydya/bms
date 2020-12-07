package com.harsha.bookmyshow.models;

import javax.persistence.*;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * The customer who will want to book seats for watching {@link Movie}s.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String name;

    @Column(unique = true)
    private String email;

    public User(Integer id, String name, String email) {
        Id = id;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
}
