package flightreservation;
import baseclass.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class FlightSearchPage
{
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id="oneway")
    private WebElement oneWayRadioButton;
    @FindBy(id="twoway")
    private WebElement roundTripRadioButton;
    @FindBy(xpath="//select[@id='passengers']")
    private WebElement passengersDropDown;
    @FindBy(xpath="//input[@value='economy']")
    private WebElement economyRadioButton;
    @FindBy(xpath="//input[@value='first']")
    private WebElement firstRadioButton;
    @FindBy(xpath="//input[@value='business']")
    private WebElement businessRadioButton;
    @FindBy(id="search-flights")
    private WebElement searchFlightsButton;
    public FlightSearchPage(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(this.driver,20);
        PageFactory.initElements(driver,this);
    }
    public void selectTrip(String oneWayOrRoundTrip)
    {
        if(oneWayOrRoundTrip.equalsIgnoreCase("one way") || oneWayOrRoundTrip.equalsIgnoreCase("oneway"))
        {
            wait.until(ExpectedConditions.visibilityOf(oneWayRadioButton)).click();
        }
        else if(oneWayOrRoundTrip.equalsIgnoreCase("round trip") || oneWayOrRoundTrip.equalsIgnoreCase("roundtrip")) {
            wait.until(ExpectedConditions.visibilityOf(roundTripRadioButton)).click();
        }
    }
    public void numberOfPassengers(String noOfPassengers) // One, Two, Three, Four
    {
        wait.until(ExpectedConditions.visibilityOf(passengersDropDown));
        Select select=new Select(passengersDropDown);
        select.selectByVisibleText(noOfPassengers);
    }
    public void chooseServiceClass(String serviceClass) // Economy, First, Business
    {
        switch(serviceClass)
        {
            case "Economy":
                wait.until(ExpectedConditions.visibilityOf(economyRadioButton)).click();
                break;
            case "First":
                wait.until(ExpectedConditions.visibilityOf(firstRadioButton)).click();
                break;
            case "Business":
                wait.until(ExpectedConditions.visibilityOf(businessRadioButton)).click();
                break;
        }
    }
    public void clickSearchFlightsButton()
    {
        wait.until(ExpectedConditions.visibilityOf(searchFlightsButton)).click();
    }

}
