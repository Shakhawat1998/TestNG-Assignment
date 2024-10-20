package testrunner;

import Pages.LoginPage;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(description = "login with admin ID and check the last registered user is displayed in dashboard and assert it against JSON")
    public void adminLoginAndCheckLastUserRegisteredIsDisplayed() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");
        //loginPage.doLogin("admin@test.com","admin123");
        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin("admin@test.com","admin123");
        }
        Map<String,String> userData = loginPage.checkLastRegisteredUserData();
        assertEquals(userData.get("tableFirstName"), userData.get("jsonFirstName"), "First name  match!");
        assertEquals(userData.get("tableEmail"), userData.get("jsonEmail"), "Email  match!");
        assertEquals(userData.get("tablePhoneNumber"), userData.get("jsonPhoneNumber"), "Phone number  match!");


    }




}
