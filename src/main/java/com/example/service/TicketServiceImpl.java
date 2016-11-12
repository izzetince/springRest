package com.example.service;

import com.example.domain.Ticket;
import com.example.domain.TicketAddForm;
import com.example.domain.User;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by izzetince on 30/10/2016.
 */

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public void addTicket(TicketAddForm form) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string
            Ticket item = new Ticket(inventoryCode, form.getItemType());
            ticketRepository.save(item);
            System.out.println(ticketRepository.findOne(item.getId()));
        }
    }

    @Override
    public Iterable<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteTicketById(long id) {
        ticketRepository.delete(id);
    }

    public Ticket getTicketById(long id) {
        return ticketRepository.findOne(id);
    }

    public Ticket assignTicket(String username, long ticketId) {
        User user = userService.getUserByUsername(username);
        Ticket ticket = getTicketById(ticketId);

        Set<Ticket> ticketList = user.getTickets();
        ticketList.add(ticket);
        user.setTickets(ticketList);

        ticket.setUser(user);

        return ticketRepository.save(ticket);
    }
}
