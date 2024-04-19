package POM;

import actions.UiActions;
import org.openqa.selenium.WebDriver;

public class FormPage {
    UiActions uiActions;
    WebDriver driver;
    public static String pageMark="//h1[text()='Complete Web Form']";
    String firstNameIdSelector="first-name";
    String lastNameIdSelector="last-name";
    String jobTitleIdSelector="job-title";
    String educationRadioBoxIdSelector="radio-button-%s";
    String sexCheckBoxSelector="checkbox-%s";
    String yearsOfExperienceDropdownIdSelector="select-menu";
    String dateIdSelector="datepicker";
    String submitBtnXpathSelector="//a[text()='Submit']";


    public FormPage(WebDriver driver) {
        this.uiActions = new UiActions(driver);
        this.driver=driver;
    }
    public void sendFirstName(String name){
        uiActions.sendKeyWord(firstNameIdSelector, UiActions.LocatorTypes.id,true, UiActions.KeysType.string,name);
    }
    public void sendLastName(String name){
        uiActions.sendKeyWord(lastNameIdSelector, UiActions.LocatorTypes.id,true, UiActions.KeysType.string,name);
    }
    public void sendJobTitle(String jobTitle){
        uiActions.sendKeyWord(jobTitleIdSelector, UiActions.LocatorTypes.id,true, UiActions.KeysType.string,jobTitle);
    }
    public void selectLevelOfEducation(int optionNumber){
        uiActions.selectCheckBox(String.format(educationRadioBoxIdSelector,optionNumber), UiActions.LocatorTypes.id);
    }
    public void selectSex(int optionNumber){
        uiActions.selectCheckBox(String.format(sexCheckBoxSelector,optionNumber), UiActions.LocatorTypes.id);
    }
    public void selectYearsOfExperience(String option){
        uiActions.selectFromDropDown(yearsOfExperienceDropdownIdSelector, UiActions.LocatorTypes.id, UiActions.SelectBy.visibleText,option);
    }
    public void setTheDate(int day,int month,int year){
        uiActions.sendKeyWord(dateIdSelector, UiActions.LocatorTypes.id,true, UiActions.KeysType.string,day+"/"+month+"/"+year);
    }
    public ThanksPage submitTheForm(){
        //uiActions.clickOn(submitBtnXpathSelector, UiActions.LocatorTypes.xpath,ThanksPage.submissionMessageXpath, UiActions.LocatorTypes.xpath, UiActions.ExpectedConditionsEnum.presenceOfElement);
        uiActions.sendKeyWord(submitBtnXpathSelector, UiActions.LocatorTypes.xpath,true, UiActions.KeysType.keyboardKeys,"ENTER");
        return new ThanksPage(driver);
    }

}
