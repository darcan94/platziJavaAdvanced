package com.darcan.amazonviewer.dao;

import com.darcan.amazonviewer.db.IDBconnection;
import com.darcan.amazonviewer.models.Chapter;
import com.darcan.amazonviewer.models.Serie;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.darcan.util.PropertiesReader.*;

public interface ChapterDao extends IDBconnection
{
    default public Chapter setChapterViewed(Chapter chapter)
    {
        try(Connection connection = dbConnect()) 
        {
                String sql = "CALL insert_viewed("+getProperty("material.two")+", "+
                                                   chapter.getId()+", "+
                                                   getProperty("idUser")+")";
                CallableStatement callableStatement = connection.prepareCall(sql);
                System.out.println(callableStatement.execute());          
        }
        catch (Exception e) 
        {
           e.printStackTrace(); 
        }
        return chapter;
    }
   
    default ArrayList<Chapter> readChapter(Serie serie)
    {
        ArrayList<Chapter> chapters = new ArrayList<>();
        try(Connection connection = dbConnect()) 
        {   
            String sql = "SELECT * FROM " + getProperty("table.chapter")+" WHERE "+getProperty("table.chapter.serie")+"="+serie.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) 
            {
                Chapter chapter = new Chapter(rs.getString(getProperty("table.title")),
                                              rs.getString(getProperty("table.genre")),
                                              rs.getString(getProperty("table.creator")),
                                              Integer.valueOf(rs.getString(getProperty("table.duration"))),
                                              Short.valueOf(rs.getString(getProperty("table.year"))),
                                              Short.valueOf(rs.getString(getProperty("table.chapter.number"))),
                                              serie);
                
                
               
                chapter.setId(Integer.valueOf(rs.getString(getProperty("table.id"))));
                chapter.setViewed(getChapterViewed(preparedStatement, connection, chapter.getId()));
                chapters.add(chapter);
                                         
            }
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return chapters;
    }

    private boolean getChapterViewed(PreparedStatement preparedStatement, Connection connection, int idChapter)
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
                preparedStatement.setInt(1, Integer.valueOf(getProperty("material.two")));
			    preparedStatement.setInt(2, idChapter);
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