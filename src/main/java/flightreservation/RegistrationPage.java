package flightreservation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class RegistrationPage {
    private WebDriverWait wait;
    private WebDriver driver;
    @FindBy(id="firstName")
    private WebElement firstName;
    @FindBy(id="lastName")
    private WebElement lastName;
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="password")
    private WebElement password;
    @FindBy(xpath="//input[@name='street']")
    private WebElement address;
    @FindBy(xpath="//input[@name='city']")
    private WebElement city;
    @FindBy(xpath="//select[@id='inputState']/option[@value='Alabama']")
    private WebElement state;
    @FindBy(xpath="//input[@name='zip']")
    private WebElement zip;
    @FindBy(id="register-btn")
    private WebElement registerBtn;

    public RegistrationPage(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(this.driver,20);
        PageFactory.initElements(driver,this);
    }
    public void enterName(String firstNme)
    {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(firstNme);
    }
    public void enterLastName(String lastNme)
    {
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lastNme);
    }
    public void enterEmailId(String emailId)
    {
        wait.until(ExpectedConditions.visibilityOf(email)).clear();
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(emailId);
    }
    public void enterPassword(String pass)
    {
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
    }
    public void enterAddress(String add)
    {
        wait.until(ExpectedConditions.visibilityOf(address)).clear();
        wait.until(ExpectedConditions.visibilityOf(address)).sendKeys(add);
    }
    public void enterCity(String yourCity)
    {
        wait.until(ExpectedConditions.visibilityOf(city)).clear();
        wait.until(ExpectedConditions.visibilityOf(city)).sendKeys(yourCity);
    }
    public void selectState()
    {
        wait.until(ExpectedConditions.visibilityOf(state)).click();
    }
    public void enterZip(String zipCode)
    {
        wait.until(ExpectedConditions.visibilityOf(zip)).clear();
        wait.until(ExpectedConditions.visibilityOf(zip)).sendKeys(zipCode);
    }
    public void clickRegisterButton()
    {
        wait.until(ExpectedConditions.visibilityOf(registerBtn)).click();
    }


}
