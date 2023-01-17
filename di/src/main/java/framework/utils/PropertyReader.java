package framework.utils;

import framework.Exceptions.PropertyNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PropertyReader {
    public static String readProperty(String propertyName, String path){
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(System.getProperty("user.dir") + "/" + path))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String str[] = line.split("=");
                if(str[0].trim().equals(propertyName.trim())){
                    return str[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new PropertyNotFoundException();
    }
}
