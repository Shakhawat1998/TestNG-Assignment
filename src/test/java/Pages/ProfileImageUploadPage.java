package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileImageUploadPage {
    @FindBy(xpath = "//button[@aria-label='account of current user']")
    public WebElement btnProfileIcon;
    @FindBy(xpath = "//li[normalize-space()='Profile']")
    public WebElement btnProfile;
    @FindBy(xpath = "//button[normalize-space()='Edit']")
    public WebElement btnEdit;
    @FindBy(className = "upload-input")
    public WebElement chooseFile;
    @FindBy(xpath = "//button[normalize-space()='Upload Image']")
    public WebElement btnUpload;
    @FindBy(xpath = "//button[normalize-space()='Update']")
    public WebElement btnUpdate;

    public ProfileImageUploadPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

}
