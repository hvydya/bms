package com.harsha.bookmyshow.repositories;

import com.harsha.bookmyshow.models.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.BitSet;
import java.util.Date;
import java.util.List;

/**
 * created on: 08/12/20
 * created by: harsha
 */

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket t WHERE t.screening_id = :screening_id AND " +
            "t.screening_date = :date AND t.show_time = :show_time", nativeQuery = true)
    List<Ticket> getBookedSeats(@Param("date") Date date, @Param("screening_id") Integer screening_id, @Param("show_time") Integer show_time);
}
