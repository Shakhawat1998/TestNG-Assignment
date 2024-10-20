package testrunner;

import Pages.RegistrationPage;
import config.CostDataset;
import config.Setup;
import Pages.AddCostPage;
import Pages.LoginPage;
import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddCostTestRunner extends Setup{


    LoginPage loginPage;
    AddCostPage addCost;
    int csvTotalCost=0;
    String expectedTotalCost;


    @BeforeTest
    public void Registration() throws IOException, ParseException, InterruptedException {
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



        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("lastName",lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        userObj.put("address",address);
        Utils.saveUserInfo("./src/test/resources/users.json",userObj);

        Thread.sleep(1000);

    }




    @Test(dataProvider = "CostCSVData", dataProviderClass = CostDataset.class, priority = 1, description = "Add 5 items from CSV dateset" ,groups = "smoke")
    public void addItemCost(String item, int quantity, String amount, String purchaseDate, String month, String remarks) throws IOException, ParseException, java.text.ParseException, InterruptedException {


        loginPage = new LoginPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");


        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");
        loginPage.doLogin(email,password);

        addCost = new AddCostPage(driver);
        addCost.btnAddCost.click();
        addCost.txtItemName.sendKeys(item);
        for(int i=1;i<quantity;i++){
            addCost.btnPlus.click();
        }
        addCost.txtAmount.sendKeys(amount);
        csvTotalCost+=Integer.parseInt(amount);

        addCost.purchaseDate.click();


        addCost.purchaseDate.clear();
        addCost.purchaseDate.sendKeys(purchaseDate);


        Select dropMonth = new Select(driver.findElement(By.id("month")));
        dropMonth.selectByVisibleText(month);
        Utils.scroll(driver,500);
        addCost.txtRemarks.sendKeys(remarks);
        addCost.btnSubmit.click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();

    }
    @Test(priority = 2, description = "calculate total cost from CSV dataset and check whether it matches with total cost from UI" ,groups = "smoke")
    public void showExpectedCostAndMatchWithTotalCost() throws IOException, ParseException, InterruptedException {

        loginPage = new LoginPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");
        addCost = new AddCostPage(driver);

        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");
        loginPage.doLogin(email,password);

        expectedTotalCost = String.valueOf(csvTotalCost);
        System.out.println(expectedTotalCost);

        Thread.sleep(2000);
        String totalCostFromUI = addCost.totalCostUI.getText();
        System.out.println(totalCostFromUI);
        String actualTotalCost = totalCostFromUI.replaceAll("[^0-9]", "");
        System.out.println(actualTotalCost);
        Assert.assertTrue(actualTotalCost.contains(expectedTotalCost), "expected and actual matched");



    }







}
