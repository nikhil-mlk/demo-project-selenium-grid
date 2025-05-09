package flightreservation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationConfirmationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id="go-to-flights-search")
    private WebElement gotoFlightSearchButton;

    public RegistrationConfirmationPage(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(this.driver,20);
        PageFactory.initElements(driver,this);
    }
   public void clickGotoFlightSearchButton()
   {
       wait.until(ExpectedConditions.visibilityOf(gotoFlightSearchButton)).click();
   }





















}
