package com.backend.SMS.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.SMS.model.Ticket;
import com.backend.SMS.model.User;
import com.backend.SMS.service.JwtService;
import com.backend.SMS.service.TicketService;
import com.backend.SMS.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class TicketResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/ticket")
    @PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<?> registerUser
        (@Valid @RequestBody Ticket ticket, HttpServletRequest request) {
        
		if(	ticket.getTitle() == null 
			|| ticket.getTicketNumber() == null 
			|| ticket.getShowTime() == null) {
			return new ResponseEntity<>
                ("title, ticketNumber, showTime are required",  HttpStatus.BAD_REQUEST);
		}

        String token = request.getHeader("Authorization").substring(7);
        String mobile = jwtService.extractMobile(token);
        User user = userService.getUserByMobile(mobile);
        ticket.setUser(user);
		
        ticketService.createTicket(ticket);

		return new ResponseEntity<>(ticket,  HttpStatus.CREATED);
	}

}
