package com.darcan.amazonviewer.db;
import java.sql.Connection;
import java.sql.DriverManager;
import static com.darcan.amazonviewer.db.DataBase.*;
public interface IDBconnection{
    default Connection dbConnect(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL+DB,USER,PASS);
            if (connection != null) {
                System.out.println("conexion con la base de datos establecida...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return connection;
        }
        
    }
}