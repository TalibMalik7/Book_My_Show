package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.RequestDTO.UserRequestDto;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto) {
       try {
           userService.addUser(userRequestDto);
           return new ResponseEntity<>("User Added Successfully", HttpStatus.ACCEPTED);
       }
       catch (Exception e){
           return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
       }
    }
}
