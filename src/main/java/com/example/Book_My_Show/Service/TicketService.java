package com.example.Book_My_Show.Service;



import com.example.Book_My_Show.Entities.Show;
import com.example.Book_My_Show.Entities.ShowSeat;
import com.example.Book_My_Show.Entities.Ticket;
import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.Exception.SeatsNotAvailableException;
import com.example.Book_My_Show.Exception.ShowNotFoundException;
import com.example.Book_My_Show.Exception.TicketNotFound;
import com.example.Book_My_Show.Exception.UserNotFoundException;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.RequestDTO.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    //manually made an constructor.
    public TicketService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public  String addTicket(TicketRequestDto ticketRequestDto) throws UserNotFoundException, ShowNotFoundException, SeatsNotAvailableException {
         User user;

         Show show;

        try{
             user  = userRepository.findById(ticketRequestDto.getUserId()).get();
         }
         catch(Exception e){
             throw new UserNotFoundException("Invalid User ID");
        }
        try{
            show = showRepository.findById(ticketRequestDto.getShowId()).get();
        }
        catch(Exception e){
            throw new ShowNotFoundException("Invalid Show ID");
        }

        Pair<Integer,Boolean> pair = checkValidityofRequestedSeats(ticketRequestDto);
        Boolean valid = pair.getSecond();
        if (!valid){
           throw new SeatsNotAvailableException("Seats Unavailable");
        }

        Integer amount = pair.getFirst();

        Ticket ticket = new Ticket();

        ticket.setMovieName(show.getMovie().getName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTotalAmount(amount);
        ticket.setTheaterName(show.getTheater().getName());
        //making booked seats string
        StringBuilder bookedSeats = new StringBuilder();
        for(String s : ticketRequestDto.getRequestedSeats()){
            bookedSeats.append(s);
            bookedSeats.append(", ");
        }
        ticket.setBookedSeats(bookedSeats.toString().substring(0,bookedSeats.toString().length()-2));  // removing last comma ans space

        // set parent;

        ticket.setUser(user);
        ticket.setShow(show);
        //

        ticket = ticketRepository.save(ticket);
        // saving ticket first to generate ticket id so data is consistent everywhere and ticket doesnt get created more than once;

        user.getTicketList().add(ticket);
        show.getListOfBookedTickets().add(ticket);  // since obj are referenced type

        //
        showRepository.save(show); // automatically saves show_seat also as show_seat is child, and we made some updates during checkValidity()
        userRepository.save(user);

        String text = "You have booked ticket for "+ticket.getBookedSeats()+"at Rs"+amount;
        String mail_info ="";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("mohammedtalib55555gmail.com");
            //message.setTo("mohammedtalib555555@gmail.com");
           message.setTo(user.getEmail());
            message.setSubject("Regarding Ticket");
            message.setText(text);
//        emailSender.send(message);

//        MimeMessage message = emailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setFrom("noreply@baeldung.com");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(text);
          javaMailSender.send(message);
          mail_info = " and mail sent successfully";
        }
        catch(Exception e){
            mail_info = " and Unable to send mail"; //will not be able to send mail as i removed password from app properties.
        }

        return "Ticket Added Successfully"+mail_info;


    }



    private Pair<Integer,Boolean> checkValidityofRequestedSeats(TicketRequestDto ticketRequestDto){

        int showId = ticketRequestDto.getShowId();

        int amount = 0;

        List<String> requestedSeats = ticketRequestDto.getRequestedSeats();

        Show show = showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats = show.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show
        for(ShowSeat showSeat : listOfSeats){

            String seatNo = showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)){

                if(showSeat.isBooked()){
                    return Pair.of(amount,false); //Since this seat cant be occupied : returning false
                }
                 amount+=showSeat.getPrice();
                 showSeat.setBooked(true);
                 showSeat.setBookedAt(new Date());
            }
        }
        //All the seats requested were available
        return Pair.of(amount,true);

    }

    public String cancelTicket(int id) throws TicketNotFound {
        Ticket ticket;
        try {
           ticket = ticketRepository.findById(id).get();
        }
        catch (Exception e){
            throw new TicketNotFound("Ticket invalid");
        }
        Show show = ticket.getShow();

        String[] Seats = ticket.getBookedSeats().split(", ");

        List<String> requestedSeats = Arrays.asList(Seats);

        List<ShowSeat> listOfSeats = show.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show and unbooking them
        for(ShowSeat showSeat : listOfSeats){

            String seatNo = showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)){


                showSeat.setBooked(false);
                showSeat.setBookedAt(null);
            }
        }

        ticketRepository.deleteById(id);

        return "Cancellation Successful";
    }

}
