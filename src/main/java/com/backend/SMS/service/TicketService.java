package com.backend.SMS.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SMS.jpa.TicketRepository;
import com.backend.SMS.model.Ticket;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    public void createTicket(Ticket ticket) {
        LocalDateTime createdTime = LocalDateTime.now();
		ticket.setCreatedTs(createdTime);
		ticket.setUpdatedTs(createdTime);
        ticketRepository.save(ticket);
    }

}
