package com.darcan.amazonviewer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.darcan.amazonviewer.db.IDBconnection;
import com.darcan.amazonviewer.models.Movie;
import static com.darcan.amazonviewer.db.DataBase.*;
public interface MovieDao extends IDBconnection{
       default Movie setMovieViewed(Movie movie){
            return movie;
       } 

        default ArrayList<Movie> read(){
        ArrayList<Movie> movies = new ArrayList<>();  
        try(Connection connection = dbConnect()) {
            String sql = "SELCT * FROM " + TMOVIE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
           
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getString(TMOVIE_TITLE), 
                    rs.getString(TMOVIE_GENRE), 
                    rs.getString(TMOVIE_CREATOR),
                    Integer.valueOf(rs.getString(TMOVIE_DURATION)),
                    Short.valueOf(rs.getString(TMOVIE_YEAR)));
                movie.setId(Integer.valueOf(rs.getString(TMOVIE_ID)));
                movie.setViewed(getMovieViewed(preparedStatement, connection, Integer.valueOf(rs.getString(TMOVIE_ID))));
                movies.add(movie);
            }
        } catch (SQLException e) {
            
        }
        return movies;
       }


       private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int id_movie){
           boolean viewed = false;
           String query = "SELECT * FROM "+TVIEWED+
                          " WHERE "+TVIEWED_IDMATERIAL+"= ?"+
                          " AND "+TVIEWED_IDELEMENT+"= ?" +
                          " AND "+TVIEWED_IDUSER+"= ?";
            ResultSet rs = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, ID_TMATERIALS[0]);
			    preparedStatement.setInt(2, id_movie);
			    preparedStatement.setInt(3, TUSER_IDUSUARIO);
			
			    rs = preparedStatement.executeQuery();
			    viewed = rs.next();
            }
            catch (Exception err) {
        
            }
           return viewed;
       }
}