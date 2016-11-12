package com.example.controller;

import com.example.domain.TicketAddForm;
import com.example.domain.TicketAssignForm;
import com.example.service.TicketService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by izzetince on 30/10/2016.
 */


//@RestController
/*
@Controller
public class TicketController {
    */
/*
    private static final String template = "Merhaba, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/ticket")
    public Ticket ticket(@RequestParam(value = "name", defaultValue = "Dünya") String name){
        return new Ticket(counter.incrementAndGet(),
                String.format(template,name));
    }*//*


    @RequestMapping(value = {"/","/home"})
    public String getHomePage(){
        return "home";
    }

}
*/


@Controller
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @RequestMapping("/tickets/add")
    public ModelAndView ticketAddPage() {
        return new ModelAndView("addTicket", "ticketForm", new TicketAddForm());
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public String handleTicketAdd(@Valid @ModelAttribute("ticketForm") TicketAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "addTicket";

        ticketService.addTicket(form);
        return "redirect:/tickets";
    }

    @RequestMapping("/tickets")
    public ModelAndView getTicketsPage() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("tickets", ticketService.getTickets());
        model.put("usernames", userService.getUsernames());
        model.put("assignForm", new TicketAssignForm());
        return new ModelAndView("tickets", model);
        //return new ModelAndView("tickets", "tickets", ticketService.getTickets());
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.DELETE)
    public String handleTicketDelete(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
        return "redirect:/tickets";
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.PUT)
    public String handleTicketAssign(@ModelAttribute("user") TicketAssignForm form, @PathVariable("id") long id) {
        ticketService.assignTicket(form.getUsername(), id);
        return "redirect:/tickets";
    }

}
