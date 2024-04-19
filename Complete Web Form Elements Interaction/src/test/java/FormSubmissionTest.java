
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormSubmissionTest extends BaseTests {


@Test
public void testForm(){
    formPage=homePage.navigateToHomePage().clickOnFormLink();
    formPage.sendFirstName("mohamed");
    formPage.sendLastName("elkassas");
    formPage.sendJobTitle("software testing engineer");
    formPage.selectLevelOfEducation(2);
    formPage.selectSex(1);
    formPage.selectYearsOfExperience("0-1");
    formPage.setTheDate(11,1,1996);
    thanksPage=formPage.submitTheForm();

    Assert.assertTrue(thanksPage.returnSubmissionStatus().getText().contains("successful"),"The form did not submit successful ");
}


}
