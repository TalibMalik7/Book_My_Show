package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)  // if we dont use enumtype.string then this seattype will be denoted as 0,1 according to their index value
    private SeatType seatType;

    private String seatNo;       // e.g. 1C, 5P

    @ManyToOne
    @JoinColumn
    private Theater theater;
}
