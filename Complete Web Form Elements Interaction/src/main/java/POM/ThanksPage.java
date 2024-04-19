package POM;

import actions.UiActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ThanksPage {
    UiActions uiActions;
    public static String submissionMessageXpath ="//div[contains (text(),'The form was successfully submitted!')]";

    public ThanksPage(WebDriver driver) {
        this.uiActions = new UiActions(driver);
    }

    public WebElement returnSubmissionStatus(){
        WebElement statusMessage= uiActions.waitUntil(submissionMessageXpath, UiActions.LocatorTypes.xpath, UiActions.ExpectedConditionsEnum.presenceOfElement);
        return statusMessage;
    }
}
