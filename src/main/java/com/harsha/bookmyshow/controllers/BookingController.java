package com.harsha.bookmyshow.controllers;

import com.harsha.bookmyshow.models.Booking;
import com.harsha.bookmyshow.models.Screen;
import com.harsha.bookmyshow.models.Screening;
import com.harsha.bookmyshow.repositories.BookingRepository;
import com.harsha.bookmyshow.repositories.ScreenRepository;
import com.harsha.bookmyshow.repositories.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * created on: 07/12/20
 * created by: harsha
 */

@Controller
@RequestMapping(path = "/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @GetMapping(path = "/get_available_seats")
    public List<Integer> getAvailableSeats(@RequestParam Integer screening_id) {
        Optional<Screening> optionalScreening = screeningRepository.findById(screening_id);
        if (!optionalScreening.isPresent()) {
            return new ArrayList<>();
        }
        List<Integer> seats = new ArrayList<>();
        BitSet bs = optionalScreening.get().getSeats();
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1)) {
            seats.add(i + 1);
        }

        return seats;
    }

    public Boolean areSeatsBookable(Screening screening, List<Integer> wantToBook) {
        BitSet bs = screening.getSeats();
        for (Integer i : wantToBook) {
            if (bs.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping(path = "/bookSeats")
    public ResponseEntity<Boolean> bookSeats(@RequestBody BookSeats bookSeats) {
        Integer screening_id = bookSeats.getScreeningId();
        Optional<Screening> optionalScreening = screeningRepository.findById(screening_id);

        // check if screening exists
        if (!optionalScreening.isPresent()) {
            return ResponseEntity.badRequest().body(false);
        }

        // check if requested screening date is in future
        if (!(new Date()).before(bookSeats.getDateOfScreening())) {
            return ResponseEntity.badRequest().body(false);
        }

        // check if seats are available
        Screening screening = optionalScreening.get();
        List<Integer> seats = bookSeats.getSeats();
        if (!areSeatsBookable(screening, seats)) {
            return ResponseEntity.badRequest().body(false);
        }

        // check if movie is getting screened at the requested screening date
        Calendar c = Calendar.getInstance();
        c.setTime(screening.getOpening());
        c.add(Calendar.DAY_OF_MONTH, screening.getPeriodOfScreening());

        if (!c.after(bookSeats.getDateOfScreening())) {
            return ResponseEntity.badRequest().body(false);
        }

        // create bitset of seats
        BitSet seatsBitset = new BitSet();
        for (Integer seat : seats) {
            seatsBitset.set(seat - 1);
        }

        // save seats as booked in screening
        BitSet booked = screening.getSeats();
        for (Integer seat : seats) {
            booked.set(seat - 1);
        }
        screening.setSeats(booked);
        screeningRepository.save(screening);

        // create booking
        Booking booking = new Booking();
        booking.setDateOfBooking(new Date());
        booking.setDateOfScreening(bookSeats.getDateOfScreening());
        booking.setScreen(screening.getScreen());
        booking.setShowTime(booking.getShowTime());
        booking.setBookedSeats(seatsBitset);
        bookingRepository.save(booking);

        return ResponseEntity.ok().body(true);
    }


}
