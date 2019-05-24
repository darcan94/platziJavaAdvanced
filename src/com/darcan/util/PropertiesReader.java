package com.darcan.util;

import java.io.FileReader;
import java.util.Properties;

public final class PropertiesReader{
    public static String getProperty(String property)
    {
        Properties properties = new Properties();
        try
        {
            properties.load(new FileReader("/home/darcan/Documentos/platzi/platziJavaAdvanced/resources/mysql.properties"));
        } 
         catch(Exception e)
        {
            e.printStackTrace();
        }
        return properties.getProperty(property);
     }
    
}