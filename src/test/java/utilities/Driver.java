package utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

//WE NEED CAUSE WE WANT TO HAVE A SINGLE driver INSTANcE
//WE are making singleton design pattern
public class Driver {
    //Create static driver instance
    //static because it can be used globally
    //private is to call this instance only in this class
    private static WebDriver driver;

    //Create a public static method to instantiate the driver

    public static WebDriver getDriver() {
        //I want to instantiate the driver instance
        //If driver is not pointing anywhere
        //If the driver is already pointing somewhere(being used)
        //We want to have onl one driver
        if (driver == null) {
            //Choreme dan başka browserlar için düzenleme yapıyoruz
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        //Close driver if it is not null
        //It means driver is pointing chrome(is being used)
        if (driver != null) {
            driver.quit();
            //driver is pointing the chrome driver is not null
            driver = null;
        }
    }
}

