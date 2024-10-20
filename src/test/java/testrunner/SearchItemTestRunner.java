package testrunner;

import Pages.LoginPage;
import Pages.SearchItemPage;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SearchItemTestRunner extends Setup {




    @Test(description = "search an item and check whether the amount of search item matches with total cost from UI", groups = "smoke")
    public void searchItemAndMatchTotalCost() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://dailyfinance.roadtocareer.net/");


        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");
        loginPage.doLogin(email,password);


        SearchItemPage search = new SearchItemPage(driver);

        search.searchBox.sendKeys("egg");
        String expectedAmount = search.tblRow1amount.getText();
        System.out.println(expectedAmount);
        Thread.sleep(1000);
        String totalCostUIText = search.totalCostUI.getText();
        System.out.println(totalCostUIText);
        String actualAmount = totalCostUIText.replaceAll("[^0-9]", "");
        System.out.println(actualAmount);
        Assert.assertTrue(actualAmount.contains(expectedAmount));

    }
}
