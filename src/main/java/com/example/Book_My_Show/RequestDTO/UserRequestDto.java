package com.example.Book_My_Show.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // this all arg is important for builder to work
@NoArgsConstructor
public class UserRequestDto {
    private String name;

    private int age;

    private String email;

    private String mobNo;

    private String address;
}
