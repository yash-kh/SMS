package com.backend.SMS.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SMS.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
    
}
