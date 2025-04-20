package com.hexaware.util;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class PropertyUtil {
    
    public static HashMap<String,String> getCredentials()
    {
        File obj = new File("creds.txt");
        HashMap<String,String> credentials = new HashMap<>();
        Scanner reader;
        

        try{
             reader = new Scanner(obj);

            while(reader.hasNextLine())
            {
                String data = reader.nextLine();
                String[] splitted = data.split(" : ");
                if((!splitted[0].equals("{")) && (!splitted[0].equals("}"))){
                    credentials.put(splitted[0].trim(),splitted[1].trim());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("File exists" + e.getMessage());
        }

        return credentials;
    }

    public static String[] getUserNameAndPassword()
    {
        HashMap<String,String> credentials = getCredentials();

        String[] userAndPassword = new String[2];

        userAndPassword[0] = credentials.get("username");
        userAndPassword[1] = credentials.get("password");

        return userAndPassword;
    }

    public static String getPropertyString()
    {
        HashMap<String,String> credentials = getCredentials();

        String connectionString = "jdbc:mysql://"+credentials.get("host")+":"+credentials.get("port")+"/"+credentials.get("database");

        return connectionString;
    }
}