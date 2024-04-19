package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class UiActions {
    WebDriver driver;
    BrowserActions browserActions;

    public UiActions(WebDriver driver){
        this.driver=driver;
    }

    public By  returnElementByLocator(String selector,LocatorTypes locType){
        By by=null;
        switch (locType){
            case id -> by=new By.ById(selector);
            case css -> by=new By.ByCssSelector(selector);
            case name -> by=new By.ByName(selector);
            case xpath -> by=new By.ByXPath(selector);
            case linkText -> by=new By.ByLinkText(selector);
        }
        return by;
    }

    public WebElement waitUntil(String selector,LocatorTypes locType, ExpectedConditionsEnum condition) {
       By b =returnElementByLocator(selector,locType);
        try {
            WebElement element = null;
            switch (condition) {
                case presenceOfElement:

                    element = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(b));
                    return element;

                case ElementToBeClickable:
                    element = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(b));
                    return element;


                default:
                    element = null;
                    Assert.fail("Wrong condition");
            }
            return element ;
        } catch (Exception e) {
            Assert.fail("Couldn't find the element because of " + e.getMessage());
            return null;
        }
    }



    public void navigateToPage(String url , String expectedElementSelector,LocatorTypes locType, ExpectedConditionsEnum conditions){
        driver.get(url);
        waitUntil(expectedElementSelector,locType,conditions);

    }

    public void selectFromDropDown(String selector,LocatorTypes locType, SelectBy selectBy,Object optionData){
        By by =returnElementByLocator(selector,locType);
        WebElement dropList= driver.findElement(by);
        Select dropdown=new Select(dropList);
        switch (selectBy){
            case index:
                if (optionData instanceof Integer) {
                    dropdown.selectByIndex((Integer) optionData);
                } else {
                    Assert.fail("given index is not an integer");
                }
                break;
            case value:
                dropdown.selectByValue(optionData.toString());
                break;
            case visibleText:
                dropdown.selectByVisibleText(optionData.toString());
                break;
        }
        }


    public void selectCheckBox(String selector,LocatorTypes locType){
        //By by= returnElementByLocator(selector,locType);
        //WebElement checkBox= driver.findElement(by);
        WebElement checkBox=waitUntil(selector,locType,ExpectedConditionsEnum.ElementToBeClickable);
        if(!checkBox.isSelected()){
            checkBox.click();
        }
    }


    public void sendKeyWord(String selector, LocatorTypes locType, boolean clear, KeysType type, String input) {
        WebElement inputField = waitUntil(selector, locType, ExpectedConditionsEnum.presenceOfElement);

        switch (type) {
            case string:
                if (clear) {
                    inputField.clear();
                }
                inputField.sendKeys(input);
                break;
            case keyboardKeys:
                try {
                    Keys key = Keys.valueOf(input);
                    inputField.sendKeys(key);

                } catch (Exception e) {
                    System.err.println("Caught an exception: " + e.getMessage());
                }
                break;
        }
    }



    public void clickOn(String elementSelector,LocatorTypes locType,String expectedElementSelector,LocatorTypes expectedLocType, ExpectedConditionsEnum condition){
        WebElement button = driver.findElement(returnElementByLocator(elementSelector, locType));
        try {
            waitUntil(elementSelector,locType, ExpectedConditionsEnum.ElementToBeClickable);
            button.click();

            if (waitUntil(expectedElementSelector, expectedLocType, condition)==null) {

                Actions actions = new Actions(driver);
                actions.doubleClick(button).perform();
            }
    }catch (Exception e){
            browserActions.refreshPage();
            waitUntil(elementSelector,locType, condition);
            button.click();

            if (waitUntil(expectedElementSelector, expectedLocType, condition)==null) {

                Actions actions = new Actions(driver);
                actions.doubleClick(button).perform();
            }
        }
    }

    public enum LocatorTypes{
        xpath,
        css,
        name,
        id,
        linkText
    }
    public enum ExpectedConditionsEnum{
        presenceOfElement,
        ElementToBeClickable
    }
    public enum SelectBy{
        value,
        visibleText,
        index
    }
    public enum KeysType{
        string,
        keyboardKeys
    }

}
