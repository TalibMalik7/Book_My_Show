package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.RequestDTO.TicketRequestDto;
import com.example.Book_My_Show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try{
              String s = ticketService.addTicket(ticketRequestDto);
              return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/cancel")
    public ResponseEntity cancelTicket(@RequestParam int id){
        try {
            String s = ticketService.cancelTicket(id);
            return new ResponseEntity<>(s,HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
