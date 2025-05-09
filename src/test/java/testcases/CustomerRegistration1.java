package testcases;
import baseclass.BaseClass;
import flightreservation.FlightSearchPage;
import flightreservation.RegistrationConfirmationPage;
import flightreservation.RegistrationPage;
import org.testng.annotations.Test;
public class CustomerRegistration1 extends BaseClass {
    @Test
    public void customerRegistration()
    {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.enterName("Nikhil");
        registrationPage.enterLastName("Malik");
        registrationPage.enterEmailId("test@test.com");
        registrationPage.enterPassword("123");
        registrationPage.enterCity("Delhi");
        registrationPage.selectState();
        registrationPage.enterZip("v8v 1e2");
        registrationPage.clickRegisterButton();
    }
    @Test(dependsOnMethods = {"customerRegistration"})
    public void clickFlightSearchButton()
    {
        RegistrationConfirmationPage registrationConfirmationPage=new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.clickGotoFlightSearchButton();
    }
    @Test(dependsOnMethods = {"clickFlightSearchButton"})
    public void flightSearch()
    {
        FlightSearchPage flightSearchPage=new FlightSearchPage(driver);
        flightSearchPage.selectTrip("one way");
        flightSearchPage.numberOfPassengers("Two");
        flightSearchPage.chooseServiceClass("First");
        flightSearchPage.clickSearchFlightsButton();
    }
}
