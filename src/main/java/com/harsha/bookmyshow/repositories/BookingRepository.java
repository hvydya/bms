package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * created on: 06/12/20
 * created by: harsha
 */

@RepositoryRestResource(collectionResourceRel = "booking", path = "crud_booking")
public interface BookingRepository extends CrudRepository<Booking, Integer> {
}
