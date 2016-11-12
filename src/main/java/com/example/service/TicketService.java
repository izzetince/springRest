package com.example.service;

import com.example.domain.Ticket;
import com.example.domain.TicketAddForm;

/**
 * Created by izzetince on 30/10/2016.
 */

public interface TicketService {

    void addTicket(TicketAddForm form);

    Iterable<Ticket> getTickets();

    void deleteTicketById(long id);

    Ticket getTicketById(long id);

    Ticket assignTicket(String username, long ticketId);
}
