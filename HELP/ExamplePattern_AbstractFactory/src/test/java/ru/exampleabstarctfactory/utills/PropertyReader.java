package ru.exampleabstarctfactory.utills;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getProperty(String propertyName){

        Properties prop = new Properties();
        InputStream input = null;
        String propertyValue = "";

        try {

            String filename = "properties/settings.properties";
            input = PropertyReader.class.getClassLoader().getResourceAsStream(filename);

            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                //return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            //System.out.println(prop.getProperty("timeout"));
            propertyValue = prop.getProperty("timeout");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propertyValue;
    }
}