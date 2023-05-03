package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.Ticket;
import com.example.Book_My_Show.ResponseDTO.TicketResponseDto;

public class TicketConvertor {

    public static TicketResponseDto ticket_ticketDto(Ticket ticket){
        return TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getMovieName())
                .showDate(ticket.getShowDate())
                .showTime(ticket.getShowTime())
                .totalAmount(ticket.getTotalAmount())
                .theaterName(ticket.getTheaterName())
                .ticketId(ticket.getTicketId())
                .build();
    }
}
