package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertor.MovieConvertor;
import com.example.Book_My_Show.Entities.Movie;
import com.example.Book_My_Show.Exception.MovieNotFoundException;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.RequestDTO.MovieRequestDto;
import com.example.Book_My_Show.ResponseDTO.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service                //->contains @Component -> make an obj of movieservice in spring ioc container
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(MovieRequestDto movieRequestDto){

        Movie movie = MovieConvertor.movieDTO_movie(movieRequestDto);

        movieRepository.save(movie);

    }

    public MovieResponseDto findMovie(int id) throws MovieNotFoundException {
        Movie movie;
        try {
            movie = movieRepository.findById(id).get();
        }
        catch (Exception e){
            throw new MovieNotFoundException("No movie with this id");
        }

        MovieResponseDto movieResponseDto = MovieConvertor.movie_movieDto(movie);

        return movieResponseDto;
    }
}
