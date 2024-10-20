package testrunner;

import Pages.RegistrationPage;
import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {

    @Test(priority = 1, description = "Register by all fields")
    public void userRegByAllFields() throws InterruptedException, IOException, ParseException {
        RegistrationPage userReg = new RegistrationPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");
        Utils.scroll(driver,500);
        userReg.btnRegister.get(1).click();
        String firstname = "Shakhawat"+ Utils.generateRandomNumber(10,99);
        String lastname = "Hossain" + Utils.generateRandomNumber(10,99);
        String email = "Shakhawat" +Utils.generateRandomNumber(10,99) +"@gmail.com";
        String password = "1234";
        String phonenumber = "01521"+ Utils.generateRandomNumber(100000,999999);
        String address = "Chittagong";

        UserModel userModel = new UserModel();

        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);
        userReg.doRegistration(userModel);

        doRegAssertion();


        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("lastName",lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        userObj.put("address",address);
        Utils.saveUserInfo("./src/test/resources/users.json",userObj);


        Thread.sleep(6000);

    }



    @Test(priority = 2, description = "Register by mandatory fields")
    public void userRegByMandatoryFields() throws InterruptedException, IOException, ParseException {
        RegistrationPage userRag = new RegistrationPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");
        Utils.scroll(driver,500);
        userRag.btnRegister.get(1).click();
        String firstname = "Shakhawat" + Utils.generateRandomNumber(10,99);
        String email = "Shakhawat" + Utils.generateRandomNumber(10,99) + "@gmail.com";
        String password = "1234";
        String phonenumber = "01521"+Utils.generateRandomNumber(100000,999999);
        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userRag.doRegistration(userModel);

        doRegAssertion();


        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        Utils.saveUserInfo("./src/test/resources/users.json",userObj);


        Thread.sleep(5000);

    }

@Test(priority = 3 , description = "Register by missing mandatory field")
public void userRegByMissingMandatoryFields() throws InterruptedException {
    RegistrationPage userReg = new RegistrationPage(driver);
    driver.get("https://dailyfinance.roadtocareer.net/");
    Utils.scroll(driver,500);
    userReg.btnRegister.get(1).click();
    String lastname= "Hossain" + Utils.generateRandomNumber(10,99);
    String email="shakhawat" + Utils.generateRandomNumber(10,99) +"@gmail.com";
    String password="1234";
    String phonenumber="01521"+Utils.generateRandomNumber(100000,999999);
    String address = "Chittagong";
    UserModel userModel= new UserModel();
    userModel.setLastname(lastname);
    userModel.setEmail(email);
    userModel.setPassword(password);
    userModel.setPhonenumber(phonenumber);
    userModel.setAddress(address);

    userReg.doRegistration(userModel);

    String validationError = userReg.txtFirstname.getAttribute("validationMessage");
    Assert.assertTrue(validationError.contains("Please fill out this field"));



}


    public void doRegAssertion() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        String successMessageExpected="successfully";
        System.out.println(successMessageActual);
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }
}
