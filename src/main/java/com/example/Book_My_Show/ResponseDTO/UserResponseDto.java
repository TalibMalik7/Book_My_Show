package com.example.Book_My_Show.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor // this all arg is important for builder to work
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String name;

    private int age;

    private String email;

    private String mobNo;

    private String address;

    private  List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
}
