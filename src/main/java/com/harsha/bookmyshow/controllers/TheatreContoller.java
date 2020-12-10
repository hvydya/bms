package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.dto.TheatreDTO;
import com.harsha.bookmyshow.models.City;
import com.harsha.bookmyshow.models.Owner;
import com.harsha.bookmyshow.models.Theatre;
import com.harsha.bookmyshow.repositories.CityRepository;
import com.harsha.bookmyshow.repositories.OwnerRepository;
import com.harsha.bookmyshow.repositories.TheatreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * created on: 10/12/20
 * created by: harsha
 */

@Api(value = "TheatreApi", description = "Theatre related API")
@RestController
@RequestMapping(path = "/api/theatre")
public class TheatreContoller {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @ApiOperation(value = "Add Theatres")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return added theatre")
    })
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

    @ApiOperation(value = "Get theatre from id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return theatre with id"),
            @ApiResponse(code = 404, message = "Theatre with Id doesn't exist")
    })
    @GetMapping()
    public ResponseEntity<Theatre> getTheatreFromId(@RequestParam Integer theatre_id) {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatre_id);
        return optionalTheatre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get all theatres")
    @GetMapping("/all")
    public ResponseEntity<Iterable<Theatre>> getAllTheatres() {
        return ResponseEntity.ok(theatreRepository.findAll());
    }

    @ApiOperation(value = "Delete theatre with id")
    @DeleteMapping()
    public ResponseEntity<String> deleteTheatre(@RequestParam Integer theatre_id) {
        theatreRepository.deleteById(theatre_id);
        return ResponseEntity.noContent().build();
    }


}
