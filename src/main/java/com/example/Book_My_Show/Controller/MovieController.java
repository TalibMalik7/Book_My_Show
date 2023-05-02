package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.RequestDTO.MovieRequestDto;
import com.example.Book_My_Show.RequestDTO.UserRequestDto;
import com.example.Book_My_Show.ResponseDTO.MovieResponseDto;
import com.example.Book_My_Show.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")    //common endpoint
public class MovieController {

    @Autowired            // insert or inject object of movieservice from ioc
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        try {
            movieService.addMovie(movieRequestDto);
            return new ResponseEntity<>("Movie Added Successfully", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find")
    public ResponseEntity findMovie(@RequestParam int id){
        try {
           MovieResponseDto movieResponseDto= movieService.findMovie(id);
           return new ResponseEntity<>(movieResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
