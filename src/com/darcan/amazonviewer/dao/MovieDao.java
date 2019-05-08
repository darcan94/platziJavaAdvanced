package com.darcan.amazonviewer.dao;
import java.util.ArrayList;

import com.darcan.amazonviewer.models.Movie;
public interface MovieDao {
       default Movie setMovieViewed(Movie movie){
            return movie;
       } 

       default ArrayList<Movie> read(){
        ArrayList<Movie> movies = new ArrayList<>();   
        return movies;
       }


       private boolean getMovieViewed(){
           return false;
       }
}