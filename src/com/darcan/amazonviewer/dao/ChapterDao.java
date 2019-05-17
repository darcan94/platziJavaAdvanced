package com.darcan.amazonviewer.dao;

import com.darcan.amazonviewer.db.IDBconnection;
import com.darcan.amazonviewer.models.Chapter;
import com.darcan.amazonviewer.models.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.darcan.amazonviewer.db.DataBase.*;

public interface ChapterDao extends IDBconnection
{
    default public Chapter setChapterViewed(Chapter chapter)
    {
        try(Connection connection = dbConnect()) 
        {
            String sql = "INSERT INTO " + TVIEWED +"("+TVIEWED_IDMATERIAL+", "+TVIEWED_IDELEMENT+", " + TVIEWED_IDUSER+")"+
                         " VALUES("+ID_TMATERIALS[2]+", "+chapter.getId()+", "+TUSER_IDUSUARIO+")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
         
            if (preparedStatement.executeUpdate() > 0)
                  System.out.println("Visto");           
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
        //Serie serie = null;
        try(Connection connection = dbConnect()) 
        {   
           // String getSerieById = "SELECT * FROM "+TSERIE+" WHERE "+TSERIE_ID+"= ?";
            String sql = "SELECT * FROM " + TCHAPTER+" WHERE "+TCHAPTER_SERIE+"="+serie.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) 
            {
               // preparedStatement = connection.prepareStatement(getSerieById);
               // preparedStatement.setInt(1, Integer.valueOf(rs.getString(TCHAPTER_SERIE)));
                //ResultSet rss = preparedStatement.executeQuery();
                //if(rss.next())
                //        serie = new Serie(rss.getString(TSERIE_TITLE), rss.getString(TSERIE_GENRE), rss.getString(TSERIE_CREATOR),
                //                          Integer.valueOf(rss.getString(TSERIE_DURATION)), Integer.valueOf(rss.getString(TSERIE_SESSIONS)));

                Chapter chapter = new Chapter(rs.getString(TCHAPTER_TITLE),
                                              rs.getString(TCHAPTER_GENRE),
                                              rs.getString(TCHAPTER_CREATOR),
                                              Integer.valueOf(rs.getString(TCHAPTER_DURATION)),
                                              Short.valueOf(rs.getString(TCHAPTER_YEAR)),
                                              Short.valueOf(rs.getString(TCHAPTER_CHAPTERS)), serie);
                
                
                
                chapter.setId(Integer.valueOf(rs.getString(TCHAPTER_ID)));
                chapter.setViewed(getChapterViewed(preparedStatement, connection, Integer.valueOf(rs.getString(TCHAPTER_ID))));
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
           String query = "SELECT * FROM "+TVIEWED+
                          " WHERE "+TVIEWED_IDMATERIAL+"= ?"+
                          " AND "+TVIEWED_IDELEMENT+"= ?" +
                          " AND "+TVIEWED_IDUSER+"= ?";
            ResultSet rs = null;
            try 
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, ID_TMATERIALS[2]);
			    preparedStatement.setInt(2, idChapter);
			    preparedStatement.setInt(3, TUSER_IDUSUARIO);
			
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