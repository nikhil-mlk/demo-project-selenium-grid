package testcases;
import baseclass.BaseClass;
import flightreservation.FlightSearchPage;
import flightreservation.RegistrationConfirmationPage;
import flightreservation.RegistrationPage;
import org.testng.annotations.Test;
public class CustomerRegistration3 extends BaseClass {
    @Test
    public void customerRegistration()
    {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.enterName("Pawan");
        registrationPage.enterLastName("Chaudhary");
        registrationPage.enterEmailId("test2@test2.com");
        registrationPage.enterPassword("59878");
        registrationPage.enterCity("Kapnur");
        registrationPage.selectState();
        registrationPage.enterZip("1133698");
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
        flightSearchPage.numberOfPassengers("Four");
        flightSearchPage.chooseServiceClass("Business");
        flightSearchPage.clickSearchFlightsButton();
    }
}
