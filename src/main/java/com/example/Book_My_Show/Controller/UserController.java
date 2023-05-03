package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.RequestDTO.UserRequestDto;
import com.example.Book_My_Show.ResponseDTO.UserResponseDto;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //for custom built in query unique attribute
    @GetMapping("/find_email")
    public ResponseEntity findUser(@RequestParam String email){
        try {
           UserResponseDto userResponseDto = userService.findbyEmail(email);
           return new ResponseEntity<>(userResponseDto,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        }

    //for custom built in query non-unique attribute
    @GetMapping("/find_age")
    public List<UserResponseDto> findbyAge(@RequestParam int age){
        return userService.findbyAge(age);
    }

    // for complex querry
    //using path variable

    @GetMapping("/find_age_complex_sql/{age}")
    public List<UserResponseDto> findbyAge_sql_query(@PathVariable("age") int age){
        return userService.findbyAge_sql_query(age);
    }
}
