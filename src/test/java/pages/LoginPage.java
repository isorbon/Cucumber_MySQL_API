package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginPage extends CommonMethods{

    @FindBy(id="txtUsername")
    public WebElement usernameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(id="btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage")
    public WebElement errorMessage;


   public LoginPage(){
       PageFactory.initElements(driver, this);
    }

    public void login(String username, String password){
    //   usernameField.sendKeys(username);
     //  passwordField.sendKeys(password);
       sendText(usernameField, ConfigReader.getPropertyValue("username"));
       sendText(passwordField, ConfigReader.getPropertyValue("password"));
       click(loginBtn);
    }


}
