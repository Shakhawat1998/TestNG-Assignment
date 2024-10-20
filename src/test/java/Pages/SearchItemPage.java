package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchItemPage {


    @FindBy(className = "search-input")
    public WebElement searchBox;
    @FindBy(xpath = "//tbody//tr[1]//td[3]")
    public WebElement tblRow1amount;
    @FindBy(xpath = "//span[normalize-space()][2]")
    public WebElement totalCostUI;



    public SearchItemPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
