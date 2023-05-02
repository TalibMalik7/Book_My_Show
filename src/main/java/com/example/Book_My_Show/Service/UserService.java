package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertor.UserConvertor;
import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.RequestDTO.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserRequestDto userRequestDto) throws Exception{
        User user = UserConvertor.userReqDTO_user(userRequestDto);
        userRepository.save(user);
    }
}
