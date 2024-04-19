package POM;


import actions.UiActions;
import org.openqa.selenium.WebDriver;

public class HomePage {
    UiActions uiActions;
    WebDriver driver;
    String homePageUrl="https://formy-project.herokuapp.com/";
    String formLinkSelector="Complete Web Form";


    public HomePage(WebDriver driver) {
        this.uiActions = new UiActions(driver);
        this.driver=driver;
    }

public HomePage navigateToHomePage(){
        uiActions.navigateToPage(homePageUrl,formLinkSelector, UiActions.LocatorTypes.linkText, UiActions.ExpectedConditionsEnum.ElementToBeClickable);
        return this;
}
public FormPage clickOnFormLink (){
        uiActions.clickOn(formLinkSelector, UiActions.LocatorTypes.linkText,FormPage.pageMark, UiActions.LocatorTypes.xpath,UiActions.ExpectedConditionsEnum.presenceOfElement);
        return new FormPage(driver);
}
}
