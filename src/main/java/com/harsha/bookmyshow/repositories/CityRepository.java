package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created on: 06/12/20
 * created by: harsha
 */

@RepositoryRestResource(collectionResourceRel = "city", path = "city")
public interface CityRepository extends CrudRepository<City, Integer> {
}
