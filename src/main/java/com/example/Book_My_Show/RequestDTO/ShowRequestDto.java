package com.example.Book_My_Show.RequestDTO;

import com.example.Book_My_Show.Enum.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {
    private LocalDate localDate;  // in postman -> "localDate" : "2023-01-01",
    //in postman -> "localTime" : "10:10:00"

    private LocalTime localTime;

    private ShowType showType;

    private int movieId;

    private int theaterId;

    private int classicSeatPrice;

    private int premiumSeatPrice;
}
