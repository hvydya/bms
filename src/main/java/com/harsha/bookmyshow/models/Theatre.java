package com.harsha.bookmyshow.models;

import javax.persistence.*;

/**
 * created on: 05/12/20
 * created by: harsha
 */

/**
 * Theatre is the place where the {@link Movie}s will be screened. Every theatre has one and only one {@link Owner}.
 * Theatres contain multiple {@link Screen}s.
 */
@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name="city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName = "id")
    private Owner owner;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Theatre(Integer id, String name, City city, Owner owner) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.owner = owner;
    }

    public Theatre() {
    }
}
