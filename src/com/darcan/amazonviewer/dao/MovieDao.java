package com.darcan.amazonviewer.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.darcan.util.PropertiesReader.getProperty;
import com.darcan.amazonviewer.db.IDBconnection;
import com.darcan.amazonviewer.models.Movie;
public interface MovieDao extends IDBconnection
{
       default Movie setMovieViewed(Movie movie)
       {
            try(Connection connection = dbConnect()) 
            {
                String sql = "CALL insert_viewed("+getProperty("material.cero")+", "+
                                                 movie.getId()+", "+
                                                 getProperty("idUser")+")";
                System.out.println(connection.prepareCall(sql).execute());
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return movie;
       } 

       default ArrayList<Movie> read()
       {
        ArrayList<Movie> movies = new ArrayList<>();  
        try(Connection connection = dbConnect())
         {
            String sql = "SELECT * FROM " + getProperty("table.movie");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
           
            while (rs.next()) 
            {
                Movie movie = new Movie(
                    rs.getString(getProperty("table.title")), 
                    rs.getString(getProperty("table.genre")), 
                    rs.getString(getProperty("table.creator")),
                    Integer.valueOf(rs.getString(getProperty("table.duration"))),
                    Short.valueOf(rs.getString(getProperty("table.year"))));

                movie.setId(Integer.valueOf(rs.getString(getProperty("table.id"))));
                movie.setViewed(getMovieViewed(preparedStatement, connection, movie.getId()));
                movies.add(movie);
            }
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
           // Main.showMenu();
        }
            return movies;
       }

       private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int id_movie)
       {
           boolean viewed = false;
           String query = "SELECT * FROM "+getProperty("table.viewed")+
                          " WHERE "+getProperty("table.viewed.idMateria")+"= ?"+
                          " AND "+getProperty("table.viewed.idElement")+"= ?" +
                          " AND "+getProperty("table.viewed.idUser")+"= ?";
            ResultSet rs = null;
            try 
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(getProperty("material.cero")));
			    preparedStatement.setInt(2, id_movie);
			    preparedStatement.setInt(3, Integer.valueOf(getProperty("idUser")));
			
			    rs = preparedStatement.executeQuery();
			    viewed = rs.next();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return viewed;
       }
}