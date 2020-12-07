package com.harsha.bookmyshow.models;

/**
 * created on: 06/12/20
 * created by: harsha
 */

/**
 * The times at which the movie will be screened. Having different times for each theatre complicates things.
 * Having common three times for all movie screenings simplifies the design.
 */
public enum ShowTime {
    MORNING,
    MATINEE,
    NIGHT
}
