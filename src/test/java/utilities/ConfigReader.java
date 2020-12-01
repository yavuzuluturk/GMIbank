package utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //This class will READ THE DATA FROM configuration.properties file!!!

    //Create a properties instance.Data Type = Properties
    private static Properties properties;

    static {
        //path of the configuration.properties file
        String path = "configuration.properties";

        try {
            //Openning the configuration.properties file
            FileInputStream fileInputStream = new FileInputStream(path);
            //loading the file
            properties = new Properties();
            properties.load(fileInputStream);
            //Closing the file
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Create  method to READ
    //This method will get the KEY and retuen the VALUE
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    //TESTING IF OUR LOGIC WORKS
    public static void main(String[] args) {
        System.out.println(properties.getProperty("browser"));
    }
}
