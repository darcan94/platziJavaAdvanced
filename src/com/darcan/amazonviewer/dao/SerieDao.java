package com.darcan.amazonviewer.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.darcan.util.PropertiesReader.*;
import com.darcan.amazonviewer.db.IDBconnection;
import com.darcan.amazonviewer.models.Chapter;
import com.darcan.amazonviewer.models.Serie;

public interface SerieDao extends IDBconnection
{
    default Serie setSerieViewed(Serie serie)
    {
        try(Connection connection = dbConnect())
        {
                String sql = "CALL insert_viewed("+getProperty("material.one")+", "+serie.getId()+", "+getProperty("isUser")+")";
                CallableStatement callableStatement = connection.prepareCall(sql);
                System.out.println(callableStatement.execute());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return serie;
    }

    default ArrayList<Serie> read()
    {
        ArrayList<Serie> series = new ArrayList<>();
        try(Connection connection = dbConnect()) 
        {
            String sql = "SELECT * FROM "+getProperty("table.serie");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Serie serie = new Serie(rs.getString(getProperty("table.title")),
                                        rs.getString(getProperty("table.genre")),
                                        rs.getString(getProperty("table.creator")),
                                        Integer.valueOf(rs.getString(getProperty("table.duration"))),5);
                            
                            serie.setId(Integer.valueOf(rs.getString(getProperty("table.id"))));
                            serie.setViewed(getSerieViewed(preparedStatement, connection, serie.getId()));
                            series.add(serie);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        series.forEach(s -> s.setChapters(Chapter.makeChapterList(s)));
        return series;
    }

    private boolean getSerieViewed(PreparedStatement preparedStatement, Connection connection, int idSerie)
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
                preparedStatement.setInt(1, Integer.valueOf(getProperty("material.one")));
			    preparedStatement.setInt(2, idSerie);
			    preparedStatement.setInt(3, Integer.valueOf( getProperty("idUser")));
			
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

