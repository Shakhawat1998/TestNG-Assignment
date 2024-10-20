package testrunner;

import Pages.LoginPage;
import Pages.ProfileImageUploadPage;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProfileImageTestRunner extends Setup {

    LoginPage loginPage;
    ProfileImageUploadPage userProfile;

    @Test(description = "login with last registered user and upload profile picture")
    public void profileImgUpload() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");
        userProfile = new ProfileImageUploadPage(driver);
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");

        loginPage.doLogin(email,password);
        userProfile.btnProfileIcon.click();
        userProfile.btnProfile.click();
        userProfile.btnEdit.click();
        String relativePath="\\src\\test\\resources\\house.jpg";
        String absolutePath=System.getProperty("user.dir")+relativePath;
        userProfile.chooseFile.sendKeys(absolutePath);
        userProfile.btnUpload.click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Utils.scroll(driver,500);
        userProfile.btnUpdate.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();


    }





}
