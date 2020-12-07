package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created on: 06/12/20
 * created by: harsha
 */

@RepositoryRestResource(collectionResourceRel = "owner", path = "owner")
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
