package testcases;
import baseclass.BaseClass;
import flightreservation.FlightSearchPage;
import flightreservation.RegistrationConfirmationPage;
import flightreservation.RegistrationPage;
import org.testng.annotations.Test;
public class CustomerRegistration2 extends BaseClass {
    @Test
    public void customerRegistration()
    {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.enterName("Dheeraj");
        registrationPage.enterLastName("Seem");
        registrationPage.enterEmailId("test@test.com");
        registrationPage.enterPassword("321");
        registrationPage.enterCity("Lucknow");
        registrationPage.selectState();
        registrationPage.enterZip("110034");
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
        flightSearchPage.selectTrip("round trip");
        flightSearchPage.numberOfPassengers("Three");
        flightSearchPage.chooseServiceClass("First");
        flightSearchPage.clickSearchFlightsButton();
    }
}
