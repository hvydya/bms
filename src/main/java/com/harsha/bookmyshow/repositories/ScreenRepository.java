package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Screen;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

/**
 * created on: 06/12/20
 * created by: harsha
 */

public interface ScreenRepository extends CrudRepository<Screen, Integer> {
}
