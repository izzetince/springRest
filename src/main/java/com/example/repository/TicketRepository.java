package com.example.repository;

import com.example.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by izzetince on 30/10/2016.
 */

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}