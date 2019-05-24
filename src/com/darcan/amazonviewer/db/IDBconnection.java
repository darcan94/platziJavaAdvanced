package com.darcan.amazonviewer.db;
import java.sql.Connection;
import java.sql.DriverManager;
import static com.darcan.util.PropertiesReader.*;
public interface IDBconnection{
    default Connection dbConnect(){
        Connection connection = null;
        try {
            Class.forName(getProperty("driver"));
            connection = DriverManager.getConnection(
                                                     getProperty("url")+
                                                     getProperty("database"),
                                                     getProperty("user"),
                                                     getProperty("password"));
            if (connection != null) {
                System.out.println("conexion con la base de datos establecida...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            return connection;
        }
        
    }
}