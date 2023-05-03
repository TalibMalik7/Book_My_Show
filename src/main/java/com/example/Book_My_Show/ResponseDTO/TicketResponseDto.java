package com.example.Book_My_Show.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor // this all arg is important for builder to work
@NoArgsConstructor
@Builder
public class TicketResponseDto {

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private int totalAmount;

    private String ticketId = UUID.randomUUID().toString();  // like a random unique identity

    private String theaterName;

    private String bookedSeats;

    private String user_name;

}
