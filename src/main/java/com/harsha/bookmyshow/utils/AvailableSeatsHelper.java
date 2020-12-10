package com.harsha.bookmyshow.utils;

import com.harsha.bookmyshow.dto.GetAvailableSeatsDTO;
import com.harsha.bookmyshow.models.Screen;
import com.harsha.bookmyshow.models.Screening;
import com.harsha.bookmyshow.models.Ticket;
import com.harsha.bookmyshow.repositories.ScreeningRepository;
import com.harsha.bookmyshow.repositories.SeatRepository;
import com.harsha.bookmyshow.repositories.TicketRepository;

import java.util.*;

/**
 * created on: 10/12/20
 * created by: harsha
 */

public class AvailableSeatsHelper {

    public static HashSet<String> get(
            GetAvailableSeatsDTO requestBody,
            ScreeningRepository screeningRepository,
            TicketRepository ticketRepository,
            SeatRepository seatRepository) {
        Integer screening_id = requestBody.getScreeningId();
        Optional<Screening> optionalScreening = screeningRepository.findById(screening_id);
        if (!optionalScreening.isPresent()) {
            System.out.println("screening not found");
            return new HashSet<>();
        }

        Screening screening = optionalScreening.get();
        Date date = requestBody.getDate();
        if (!Utils.isMovieScreeningOnDate(screening, date)) {
            System.out.println("Movie not screening on date");
            return new HashSet<>();
        }

        HashSet<String> bookedSeatsSet = new HashSet<>();
        List<Ticket> bookedTickets = ticketRepository.getBookedSeats(date, screening_id);
        for (Ticket t : bookedTickets) {
            String bookedSeats = t.getBookedSeats();
            bookedSeatsSet.addAll(Arrays.asList(bookedSeats.split(",")));
        }

        HashSet<String> seats = new HashSet<>(seatRepository.getSeatNumbers(screening.getScreen().getId()));
        for (String booked : bookedSeatsSet) seats.remove(booked);

        return seats;
    }
}
