package com.darcan.amazonviewer.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.darcan.amazonviewer.db.DataBase.*;
import com.darcan.amazonviewer.db.IDBconnection;
import com.darcan.amazonviewer.models.Serie;

public interface SerieDao extends IDBconnection
{
    default Serie setSerieViewed(Serie serie)
    {
        try(Connection connection = dbConnect())
        {
            String sql = "INSERT INTO" + TVIEWED +"("+TVIEWED_IDMATERIAL+", "+TVIEWED_IDELEMENT+", " + TVIEWED_IDUSER+")"+
                         "VALUES("+ID_TMATERIALS[1]+", "+serie.getId()+", "+TUSER_IDUSUARIO+")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(preparedStatement.executeUpdate() > 0)
                System.out.println("Visto");
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
            String sql = "SELECT * FROM "+TSERIE;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Serie serie = new Serie(rs.getString(TSERIE_TITLE),
                                        rs.getString(TSERIE_GENRE),
                                        rs.getString(TSERIE_CREATOR),
                                        Integer.valueOf(rs.getString(TSERIE_DURATION)),5);
                            
                            serie.setId(Integer.valueOf(rs.getString(TSERIE_ID)));
                            serie.setViewed(getChapterViewed(preparedStatement, connection, Integer.valueOf(rs.getString(TSERIE_ID))));
                            series.add(serie);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return series;
    }

    private boolean getChapterViewed(PreparedStatement preparedStatement, Connection connection, int idSerie)
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
                preparedStatement.setInt(1, ID_TMATERIALS[1]);
			    preparedStatement.setInt(2, idSerie);
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

