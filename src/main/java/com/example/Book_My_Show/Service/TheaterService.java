package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertor.TheaterConvertor;
import com.example.Book_My_Show.Entities.Theater;
import com.example.Book_My_Show.Entities.TheaterSeat;
import com.example.Book_My_Show.Enum.SeatType;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Repository.TheaterSeatRepository;
import com.example.Book_My_Show.RequestDTO.TheaterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto) throws Exception{

        if(theaterRequestDto.getName()==null||theaterRequestDto.getLocation()==null){
            throw new Exception("Name and location should valid");
        }


        Theater theater = TheaterConvertor.theaterReqDto_theater(theaterRequestDto);
        List<TheaterSeat> theaterSeatList =  theaterSeat(theaterRequestDto,theater);
        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);
        return "Theater added successfully";
    }

    public List<TheaterSeat> theaterSeat(TheaterRequestDto theaterRequestDto, Theater theater){

      int noOf_classic = theaterRequestDto.getClassicSeatsCount();
      int noOf_premium = theaterRequestDto.getPremiumSeatsCount();

      List<TheaterSeat> theaterSeatList = new ArrayList<>();

      //Classic Seat
      for(int i = 1;i<=noOf_classic;i++){

          TheaterSeat theaterSeat = new TheaterSeat();  //making obj by using new keyword
          theaterSeat.setTheater(theater);
          theaterSeat.setSeatNo(i+"C");
          theaterSeat.setSeatType(SeatType.CLASSIC);
          theaterSeatList.add(theaterSeat);
      }

        //Create the premium Seats
        for(int count=1;count<=noOf_premium;count++){

            TheaterSeat theaterSeat = TheaterSeat.builder().    //making object using builder
                    seatType(SeatType.PREMIUM).seatNo(count+"P").theater(theater).build();
            theaterSeatList.add(theaterSeat);
        }
      return theaterSeatList;
    }

}
