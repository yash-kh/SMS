package com.backend.SMS.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=2, message = "Title should have at least 2 characters")
	private String title;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

	private String ticketNumber;

	private int actualPrice;

	private int sellingPrice;

	private LocalDateTime showTime;
    
	private int seatNumber;

	private String type;

	private String imgUrl;

	private String status = "open";

	private LocalDateTime createdTs;

	private LocalDateTime updatedTs;

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", title=" + title + ", user=" + user 
                + ", ticketNumber=" + ticketNumber
                + ", actualPrice=" + actualPrice + ", sellingPrice=" 
                + sellingPrice + ", showTime=" + showTime
                + ", seatNumber=" + seatNumber + ", type=" + type + ", imgUrl=" 
                + imgUrl + ", status=" + status
                + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
    }
    
}
