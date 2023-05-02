package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertor.ShowConvertor;
import com.example.Book_My_Show.Entities.*;
import com.example.Book_My_Show.Enum.SeatType;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.RequestDTO.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(ShowRequestDto showRequestDto) throws Exception{
        Movie movie;
        Theater theater;
        try{
            movie = movieRepository.findById(showRequestDto.getMovieId()).get();
            theater  = theaterRepository.findById(showRequestDto.getTheaterId()).get();
        }
        catch (Exception e){
            throw new Exception("Invalid details");
        }
        Show show = ShowConvertor.showReqDto_show(showRequestDto);
        //setting foreign key attribute in show
        show.setMovie(movie);
        show.setTheater(theater);

        List<ShowSeat> showSeatList = showSeat(showRequestDto,show);
        show.setListOfShowSeats(showSeatList);



//        // setting show in parent list
//        movie.getShowList().add(show);      //hew we are saving saving show which has no id set yet so it will be cascaded if parent is saved
//        theater.getShowList().add(show);

        show = showRepository.save(show);   // so that id of show is created now and it will not be cascaded by different parents again and again

        // setting show with id attribute in  parent
        movie.getShowList().add(show);
        theater.getShowList().add(show);

       // saving parents in their respective repo
        movieRepository.save(movie);  //now this time we have show with id so while cascading it will update and not create new show
        theaterRepository.save(theater);  // now that 2 time show saving problem solved -problem explained below

        /*
        actuallty what was happening was we were saving show without id generated in movie and theater and then saving movie and theater
        in their repository and now due to cascade all and show not having id ... it was trating movie and theater show differently and saved them both
        generating 2 shows
         */

        return "Show Added Successfully";
    }

    public List<ShowSeat> showSeat(ShowRequestDto showRequestDto,Show show){

        List<ShowSeat> showSeatList = new ArrayList<>();

        List<TheaterSeat> theaterSeatList = show.getTheater().getTheaterSeatList();

        for(TheaterSeat ts : theaterSeatList){
            ShowSeat showSeat = new ShowSeat();

                showSeat.setBooked(false);
                showSeat.setSeatNo(ts.getSeatNo());
                showSeat.setSeatType(ts.getSeatType());

            if(ts.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showRequestDto.getClassicSeatPrice());
            }
            else{
                showSeat.setPrice(showRequestDto.getPremiumSeatPrice());
            }
            showSeat.setShow(show);
           showSeatList.add(showSeat);
        }
        return showSeatList;
    }
}
