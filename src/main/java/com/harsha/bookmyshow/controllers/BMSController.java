package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.Utils;
import com.harsha.bookmyshow.dto.GetAvailableSeatsDTO;
import com.harsha.bookmyshow.dto.SeatBookingDTO;
import com.harsha.bookmyshow.models.*;
import com.harsha.bookmyshow.repositories.ScreeningRepository;
import com.harsha.bookmyshow.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Integer> getAvailableSeats(@RequestBody GetAvailableSeatsDTO requestBody) {
        return new ArrayList<>(getAvailableSeatsHelper(requestBody));
    }

    private HashSet<Integer> getAvailableSeatsHelper(GetAvailableSeatsDTO requestBody) {
        Integer screening_id = requestBody.getScreeningId();
        Optional<Screening> optionalScreening = screeningRepository.findById(screening_id);
        if (!optionalScreening.isPresent()) {
            System.out.println("screening not found");
            return new HashSet<>();
        }

        Screening screening = optionalScreening.get();
        Date date = requestBody.getDate();
        if (!isMovieScreeningOnDate(screening, date)) {
            System.out.println("Movie not screening on date");
            return new HashSet<>();
        }

        HashSet<Integer> seats = new HashSet<>();
        List<Ticket> bookedTickets = ticketRepository.getBookedSeats(date, screening_id, requestBody.getShowTime().ordinal());
        BitSet allBookedSeats = new BitSet();
        for (Ticket t : bookedTickets) {
            BitSet bs = t.getBookedSeats();
            for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1)) {
                allBookedSeats.set(i);
            }
        }
        for (int i = 0; i < Screen.NUM_SEATS; i++) {
            if (!allBookedSeats.get(i)) seats.add(i + 1);
        }

        return seats;
    }

    private boolean isMovieScreeningOnDate(Screening screening, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(screening.getOpening());
        c.add(Calendar.DAY_OF_MONTH, screening.getPeriodOfScreening());
        return c.getTime().after(date);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/book_seats")
    public Boolean bookSeats(@RequestBody SeatBookingDTO bookSeats) {
        Integer screening_id = bookSeats.getScreeningId();
        Date date = bookSeats.getDateOfScreening();
        List<Integer> seatsToBook = bookSeats.getSeats();
        ShowTime showTime = bookSeats.getShowTime();

        if (!Utils.isDateInFuture(date)) {
            return false;
        }

        BitSet seatsToBookBS = new BitSet();
        HashSet<Integer> availableSeats = getAvailableSeatsHelper(new GetAvailableSeatsDTO(screening_id, date, showTime));
        for (Integer seat : seatsToBook) {
            if (!availableSeats.contains(seat)) {
                return false;
            }
            seatsToBookBS.set(seat - 1);
        }

        Ticket t = new Ticket(seatsToBookBS, screeningRepository.findById(screening_id).get(), showTime, date, new Date());
        System.out.println(t);
        ticketRepository.save(t);
        return true;
    }
}
