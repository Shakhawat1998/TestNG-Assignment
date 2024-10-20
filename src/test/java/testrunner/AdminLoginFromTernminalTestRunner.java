package testrunner;

import Pages.LoginPage;
import config.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginFromTernminalTestRunner extends Setup {

    @Test(description = "Admin Login from terminal")
    public void adminLoginFromTerminal(){
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");
        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin("admin@test.com","admin123");
        }
        //loginPage.doLogin("admin@test.com","admin123");
        String headerActual= driver.findElement(By.tagName("h2")).getText();
        String headerExpected="Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));

    }

}
