package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * created on: 06/12/20
 * created by: harsha
 */

public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
