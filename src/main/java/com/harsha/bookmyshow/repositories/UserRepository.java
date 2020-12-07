package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * created on: 06/12/20
 * created by: harsha
 */

public interface UserRepository extends CrudRepository<User, Integer> {
}
