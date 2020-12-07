package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Theatre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created on: 06/12/20
 * created by: harsha
 */

@RepositoryRestResource(collectionResourceRel = "theatre", path = "theatre")
public interface TheatreRepository extends CrudRepository<Theatre, Integer> {
}
