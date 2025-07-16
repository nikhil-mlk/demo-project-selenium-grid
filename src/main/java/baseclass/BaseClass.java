package baseclass;
import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;



public class BaseClass {
    //---private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected WebDriver driver;
    private Properties properties;
    public ChromeOptions options;

    @BeforeClass
    public void setupDriver() {

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
            } else if (browserName.equalsIgnoreCase("chrome") && runOnGrid.equalsIgnoreCase("no")) {
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
            reader.close();
        } catch (Exception a) {
        }
    }
    @AfterClass
    public void closeBrowser()
    {
        this.driver.quit();
    }
}

