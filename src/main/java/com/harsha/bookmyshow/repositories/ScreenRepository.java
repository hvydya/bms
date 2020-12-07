package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Screen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created on: 06/12/20
 * created by: harsha
 */


@RepositoryRestResource(collectionResourceRel = "screen", path = "screen")
public interface ScreenRepository extends CrudRepository<Screen, Integer> {
}
