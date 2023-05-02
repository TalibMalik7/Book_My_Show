package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.RequestDTO.UserRequestDto;

public class UserConvertor {          // class deals with user related object
// static to avoid calling via objects

    //convertor is just for tidiness of code or say more readability neat and clean.
    public static User userReqDTO_user(UserRequestDto userRequestDto){
        return User.builder().age(userRequestDto.getAge()).address(userRequestDto.getAddress())
                .email(userRequestDto.getEmail()).name(userRequestDto.getName()).mobNo(userRequestDto.getMobNo())
                .build();

    }
}
