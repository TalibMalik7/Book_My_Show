package com.example.Book_My_Show.RequestDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketRequestDto {

    private int showId;

    private List<String> requestedSeats = new ArrayList<>();      //1P,2P,3C .. in postman use square bracket -> :["1P","1C","2C"]

    private int userId;
}
