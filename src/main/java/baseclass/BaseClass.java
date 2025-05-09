package baseclass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
public class BaseClass {
    protected WebDriver driver;
    private Properties properties;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    public ChromeOptions options;
    @BeforeClass
    public void setupDriver(){

        try {
            properties = new Properties();
            BufferedReader reader = new BufferedReader(new FileReader("./demo.properties"));
            properties.load(reader);

            String browserName = System.getProperty("browserName", properties.getProperty("browserName"));
            String runOnGrid = System.getProperty("runOnGrid", properties.getProperty("runOnGrid"));
            String hubURL = System.getProperty("hubURL", properties.getProperty("hubURL"));
            String appURL = System.getProperty("appUrl", properties.getProperty("appUrl"));

            if (browserName.equalsIgnoreCase("chrome") && runOnGrid.equalsIgnoreCase("yes")) {
                MutableCapabilities dc = new ChromeOptions();
                this.driver = new RemoteWebDriver(new URL(hubURL), dc);
                this.driver.manage().window().maximize();
                this.driver.get(appURL);
            }
            else if (browserName.equalsIgnoreCase("chrome") && runOnGrid.equalsIgnoreCase("no")) {
                options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("incognito");
                options.addArguments("disk-cache-size=0");
                this.driver = new ChromeDriver(options);
                this.driver.manage().deleteAllCookies();
                this.driver.manage().window().maximize();
                this.driver.get(appURL);
            }
            /* if (properties.getProperty("runOnGrid").equalsIgnoreCase("yes")) {

                this.driver = getRemoteDriver();
                this.driver.get("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
            } else {
                this.driver = getLocalDriver();
                this.driver.get("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
            }*/
            reader.close();
        }
        catch(Exception a)
        {}
    }
   /* private WebDriver getLocalDriver() {
            this.driver=new ChromeDriver();
            this.driver.manage().window().maximize();
            return this.driver;
    }
    private WebDriver getRemoteDriver() throws MalformedURLException {
        MutableCapabilities dc=null;
        try {
            properties = new Properties();
            BufferedReader reader = new BufferedReader(new FileReader("./demo.properties"));
            properties.load(reader);
            if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
                dc = new ChromeOptions();
            }
            else
                dc = new FirefoxOptions();
        }
        catch(Exception a)
        {}
        return new RemoteWebDriver(new URL("http://10.10.10.215:4444"), dc);

    }*/
   /* @AfterClass
    public void closeBrowser()
    {
        this.driver.quit();
    }*/
}
