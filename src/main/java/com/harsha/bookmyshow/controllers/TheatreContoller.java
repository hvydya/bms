package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.dto.TheatreDTO;
import com.harsha.bookmyshow.models.City;
import com.harsha.bookmyshow.models.Owner;
import com.harsha.bookmyshow.models.Theatre;
import com.harsha.bookmyshow.repositories.CityRepository;
import com.harsha.bookmyshow.repositories.OwnerRepository;
import com.harsha.bookmyshow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * created on: 10/12/20
 * created by: harsha
 */

@RestController
@RequestMapping(path = "/api/theatre")
public class TheatreContoller {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @PostMapping("")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreDTO theatreDTO) {
        Optional<City> optionalCity = cityRepository.findById(theatreDTO.getCity_id());
        Optional<Owner> optionalOwner = ownerRepository.findById(theatreDTO.getOwner_id());

        if (!optionalCity.isPresent() || !optionalOwner.isPresent()) {
            return ResponseEntity.badRequest().body("city or owner or both are not present");
        }

        Theatre t = theatreRepository.save(new Theatre(theatreDTO.getName(), optionalCity.get(), optionalOwner.get()));
        return ResponseEntity.accepted().body(t.toString());
    }
}
