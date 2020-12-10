package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.Utils;
import com.harsha.bookmyshow.dto.ScreeningDTO;
import com.harsha.bookmyshow.models.City;
import com.harsha.bookmyshow.models.Movie;
import com.harsha.bookmyshow.models.Screen;
import com.harsha.bookmyshow.models.Screening;
import com.harsha.bookmyshow.repositories.CityRepository;
import com.harsha.bookmyshow.repositories.MovieRepository;
import com.harsha.bookmyshow.repositories.ScreenRepository;
import com.harsha.bookmyshow.repositories.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * created on: 10/12/20
 * created by: harsha
 */

@RestController
@RequestMapping("/api/screening")
public class ScreeningController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    CityRepository cityRepository;

    @PostMapping("")
    public ResponseEntity<String> addScreening(@RequestBody ScreeningDTO screeningDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(screeningDTO.getMovie_id());
        Optional<Screen> optionalScreen = screenRepository.findById(screeningDTO.getScreen_id());
        Date opening = screeningDTO.getOpening();
        if (!Utils.isDateInFuture(opening)) {
            return ResponseEntity.badRequest().body("Date of opening is not in future");
        }

        if (!optionalMovie.isPresent() || !optionalScreen.isPresent()) {
            return ResponseEntity.badRequest().body("Movie or Screen or both are not present");
        }

        // Check if screening is schedule-able at this date slot.
        Screen screen = optionalScreen.get();
        Date closing = Utils.addDaysToDate(opening, screeningDTO.getPeriod_of_screening());
        Integer count = screeningRepository.getCountOfRunningScreeningsBetweenDates(opening, closing, screen.getId());
        if (count > 0) {
            return ResponseEntity.badRequest().body("A Screening at this screen is already scheduled at this date. Select a further date");
        }

        Screening s = screeningRepository.save(new Screening(optionalMovie.get(), opening, screeningDTO.getPeriod_of_screening(), optionalScreen.get()));
        return ResponseEntity.ok(s.toString());
    }

    @GetMapping("/find_cities")
    public ResponseEntity<Iterable<City>> findCitiesWithScreening() {
        return ResponseEntity.ok(cityRepository.findAllById(screeningRepository.findCitiesWithScreening()));
    }

    @GetMapping("/find_screenings")
    public ResponseEntity<Iterable<Screening>> findScreeningsInCity(@RequestParam Integer city_id) {
        return ResponseEntity.ok(screeningRepository.findAllById(screeningRepository.findMoviesWithCity(city_id)));
    }

}
