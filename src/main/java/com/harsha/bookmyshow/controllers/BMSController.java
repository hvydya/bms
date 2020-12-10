package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.dto.GetAvailableSeatsDTO;
import com.harsha.bookmyshow.dto.SeatBookingDTO;
import com.harsha.bookmyshow.models.*;
import com.harsha.bookmyshow.repositories.ScreeningRepository;
import com.harsha.bookmyshow.repositories.SeatRepository;
import com.harsha.bookmyshow.repositories.TicketRepository;
import com.harsha.bookmyshow.utils.AvailableSeatsHelper;
import com.harsha.bookmyshow.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * created on: 08/12/20
 * created by: harsha
 */

/**
 * 1. The system should allow the registration of new movie theaters
 * 2. Owners should be able to add new movie shows
 * 3. The system should be able to list down cities where it's cinemas are located.
 * 4. Upon selecting the city, the system should display the movies released in that particular city to that user.
 * 5. The user should be able to select the show from a cinema and book their tickets.
 * 6. The user should be able to select multiple seats according to their choice.
 * 7. The user should be able to distinguish between available seats from the booked ones.
 * 8. The system should serve the tickets First In First Out manner
 */

@RestController
@RequestMapping(path = "/api/bms")
public class BMSController {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    // 1. Builtin api (/api/v0/theatre). Refer TheatreRepository.java for more details.

    // 2. Builtin api (/api/v0/crud_screening). Refer ScreeningRepository and Screening for more details.

    // 3. Builtin api with custom sql query to fetch the cities.

    // (/api/v0/crud_screening/search/findCitiesWithScreening). Refer ScreeningRepository.

    // 4. Builtin api with custom sql query to fetch movies actively screening in that city.

    // (/api/v0/crud_screening/search/findMoviesWithCity?city_id=2)

    // 7. Ref below
    // getAvailableSeats(screening_id, date_the_user_wants_to_watch_movie)
    // TODO : Change this
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/get_available_seats")
    public List<String> getAvailableSeats(@RequestBody GetAvailableSeatsDTO requestBody) {
        List<String> seats = new ArrayList<>();
        AvailableSeatsHelper.get(requestBody, screeningRepository, ticketRepository, seatRepository)
                .stream().sorted().forEach(seats::add);
        return seats;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/book_seats")
    public ResponseEntity<String> bookSeats(@RequestBody SeatBookingDTO bookSeats) {
        Integer screening_id = bookSeats.getScreeningId();
        Date date = bookSeats.getDateOfScreening();
        List<String> seatsToBook = bookSeats.getSeats();

        if (!Utils.isDateInFuture(date)) {
            return ResponseEntity.badRequest().body("screening for the given date is over.");
        }

        HashSet<String> availableSeats = AvailableSeatsHelper.get(new GetAvailableSeatsDTO(screening_id, date),
                screeningRepository, ticketRepository, seatRepository);

        for (String seat : seatsToBook) {
            if (!availableSeats.contains(seat)) {
                return ResponseEntity.badRequest().body("Some error occurred while booking");
            }
        }
        String seats = String.join(",", seatsToBook);
        Ticket t = new Ticket(seats, screeningRepository.findById(screening_id).get(), date, new Date());
        System.out.println(t);
        ticketRepository.save(t);

        return ResponseEntity.ok(t.toString());
    }
}
