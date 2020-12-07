package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created on: 06/12/20
 * created by: harsha
 */

@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
