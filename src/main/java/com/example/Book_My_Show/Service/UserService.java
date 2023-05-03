package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertor.TicketConvertor;
import com.example.Book_My_Show.Convertor.UserConvertor;
import com.example.Book_My_Show.Entities.Ticket;
import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.Exception.UserNotFoundException;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.RequestDTO.UserRequestDto;
import com.example.Book_My_Show.ResponseDTO.TicketResponseDto;
import com.example.Book_My_Show.ResponseDTO.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserRequestDto userRequestDto) throws Exception{
        User user = UserConvertor.userReqDTO_user(userRequestDto);
        userRepository.save(user);
    }

    public UserResponseDto findbyEmail(String email) throws UserNotFoundException{
        User user;
        try {
             user = userRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found");
        }

        List<Ticket> ticketList = user.getTicketList();
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for(Ticket ticket : ticketList){
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto = TicketConvertor.ticket_ticketDto(ticket);
            ticketResponseDto.setUser_name(user.getName());
            ticketResponseDtoList.add(ticketResponseDto);
        }

        UserResponseDto userResponseDto = UserConvertor.user_userResponseDto(user);

        userResponseDto.setTicketResponseDtoList(ticketResponseDtoList);

        return userResponseDto;
    }

    public List<UserResponseDto> findbyAge(int age){

        List<User> userList = userRepository.findByAge(age);

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user : userList){
            List<Ticket> ticketList = user.getTicketList();
            List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
            for(Ticket ticket : ticketList){
                TicketResponseDto ticketResponseDto = new TicketResponseDto();
                ticketResponseDto = TicketConvertor.ticket_ticketDto(ticket);
                ticketResponseDto.setUser_name(user.getName());
                ticketResponseDtoList.add(ticketResponseDto);
            }

            UserResponseDto userResponseDto = UserConvertor.user_userResponseDto(user);

            userResponseDto.setTicketResponseDtoList(ticketResponseDtoList);

            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }


    public List<UserResponseDto> findbyAge_sql_query(int age){

        List<User> userList = userRepository.custom_query_age(age);

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user : userList){
            List<Ticket> ticketList = user.getTicketList();
            List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
            for(Ticket ticket : ticketList){
                TicketResponseDto ticketResponseDto = new TicketResponseDto();
                ticketResponseDto = TicketConvertor.ticket_ticketDto(ticket);
                ticketResponseDto.setUser_name(user.getName());
                ticketResponseDtoList.add(ticketResponseDto);
            }

            UserResponseDto userResponseDto = UserConvertor.user_userResponseDto(user);

            userResponseDto.setTicketResponseDtoList(ticketResponseDtoList);

            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }
}
