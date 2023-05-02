package com.example.Book_My_Show.ResponseDTO;

import com.example.Book_My_Show.Enum.Genre;
import com.example.Book_My_Show.Enum.Language;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponseDto {

    private String movieName;

    private double ratings;

    private int duration;

    private Language language;

    private Genre genre;
}
