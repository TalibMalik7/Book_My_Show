package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.Theater;
import com.example.Book_My_Show.RequestDTO.TheaterRequestDto;

public class TheaterConvertor {

    public static Theater theaterReqDto_theater(TheaterRequestDto theaterRequestDto){

        return Theater.builder().location(theaterRequestDto.getLocation())
                .name(theaterRequestDto.getName()).build();

    }
}
