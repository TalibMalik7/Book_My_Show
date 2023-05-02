package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enum.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import  java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shows")   // you have to change name as it is reserved keyword and will give eroor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;   // show only daate and is  very customizzable format  // yyyy-mm-dd

    private LocalTime showTime;    // show only time and is  very customizzable  // hh-mm-ss


    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;           // date shows date+time

    @UpdateTimestamp
    private Date updatedOn;


    //This is child wrt to the movieEntity
    @ManyToOne
    @JoinColumn
    private Movie movie;


    @ManyToOne
    @JoinColumn    // in this annotation by deafult foreign key is primary key of theater ... we can change it using (refrencecloumn ="") for making other clmn as foreign key if its not null and unique
    private Theater theater;

    //Show is parent wrt to ticket
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket> listOfBookedTickets = new ArrayList<>();

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeat> listOfShowSeats = new ArrayList<>();
    

}
