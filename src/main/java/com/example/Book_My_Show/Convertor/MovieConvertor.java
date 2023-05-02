package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.Movie;
import com.example.Book_My_Show.RequestDTO.MovieRequestDto;
import com.example.Book_My_Show.ResponseDTO.MovieResponseDto;

public class MovieConvertor {

    public static Movie movieDTO_movie(MovieRequestDto movieRequestDto){
        return Movie.builder()
                .name(movieRequestDto.getMovieName()).duration(movieRequestDto.getDuration())
                .genre(movieRequestDto.getGenre()).language(movieRequestDto.getLanguage()).rating(movieRequestDto.getRatings()).build();
    }

    public static MovieResponseDto movie_movieDto(Movie movie){
        return MovieResponseDto.builder()
                .movieName(movie.getName())
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .ratings(movie.getRating())
                .language(movie.getLanguage())
                .build();
    }
}
