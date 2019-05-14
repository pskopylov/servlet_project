package com.epam;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ReadProperties {

    static Properties getProperties(String locale){
        Properties properties = null;
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/main/resources/" + locale + ".properties");
            properties.load(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return properties;
    }
}
