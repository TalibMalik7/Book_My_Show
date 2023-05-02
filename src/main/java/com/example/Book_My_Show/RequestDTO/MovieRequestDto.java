package com.example.Book_My_Show.RequestDTO;

import com.example.Book_My_Show.Enum.Genre;
import com.example.Book_My_Show.Enum.Language;
import lombok.Data;

@Data
public class MovieRequestDto {

    private String movieName;

    private double ratings;

    private int duration;

    private Language language;

    private Genre genre;
}
