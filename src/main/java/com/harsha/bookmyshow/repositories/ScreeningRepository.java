package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.City;
import com.harsha.bookmyshow.models.Movie;
import com.harsha.bookmyshow.models.Screening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * created on: 06/12/20
 * created by: harsha
 */

@RepositoryRestResource(collectionResourceRel = "screening", path = "crud_screening")
public interface ScreeningRepository extends CrudRepository<Screening, Integer> {

    @Query(value = "SELECT DISTINCT t.city_id FROM \n" +
            "((screening sc INNER JOIN screen s ON sc.screen_id = s.id) INNER JOIN theatre t ON s.theatre_id = t.id)",
            nativeQuery = true
    )
    List<City> findCitiesWithScreening();

    @Query(value = "SELECT DISTINCT sc.movie_id FROM\n" +
            "((screening sc INNER JOIN screen s ON sc.screen_id = s.id) \n" +
            "INNER JOIN theatre t ON s.theatre_id = t.id) WHERE t.city_id = :city_id", nativeQuery = true)
    List<Movie> findMoviesWithCity(@Param("city_id") Integer city_id);
}
