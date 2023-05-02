package com.example.Book_My_Show.RequestDTO;

import lombok.Data;

@Data
public class TheaterRequestDto {

    private String name;
    private String location;

    private int classicSeatsCount;

    private int premiumSeatsCount;
}
