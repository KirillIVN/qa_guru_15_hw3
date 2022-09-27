package demo.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        String firstName = "Robert";
        String secondName = "Svan";
        String email = "r.sv@gmail.com";
        String mPhone = "8097545152";
        String perSubject1 = "Accounting";
        String currAddress = "Current Address 1";


        //tests for main page;
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#RightSide_Advertisement').remove()");

        $x("//*[@id='firstName']").setValue(firstName);
        $x("//*[@id='lastName']").setValue(secondName);
        $x("//*[@id='userEmail']").setValue(email);
        $x("//*[@id='gender-radio-1']").parent().click();
        $x("//*[@id='userNumber']").setValue(mPhone);
        $x("//*[@id='dateOfBirth']/div[1]/div").click();
        $x("//*[@class='react-datepicker__month-select']").click();
        $x("//*[@class='react-datepicker__month-select']").selectOptionByValue("7");
        $x("//*[@class='react-datepicker__year-select']").click();
        $x("//*[@class='react-datepicker__year-select']").selectOptionByValue("1975");
        $x("//*[@class='react-datepicker__day react-datepicker__day--004']").click();
        $x("//*[@id='subjectsInput']").setValue(perSubject1).pressEnter();
        $x("//*[@id='hobbies-checkbox-1']").parent().click();
        $x("//*[@id='hobbies-checkbox-3']").parent().click();
        $x("//*[@id='uploadPicture']").uploadFromClasspath("cat1.jpg");
        $x("//*[@id='currentAddress']").setValue(currAddress);
        $x("//*[@id='state']").click();
        $x("//*[@id='react-select-3-option-2']").click();
        $x("//*[@id='city']").click();
        $x("//*[@id='react-select-4-option-1']").click();
        $x("//*[@id='submit']").click();


        //test for modal page;
        $(".modal-dialog modal-lg).shouldHave(appear");
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive table").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + secondName));
        $(".table-responsive table").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive table").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive table").$(byText("Mobile")).parent().shouldHave(text(mPhone));
        $(".table-responsive table").$(byText("Date of Birth")).parent().shouldHave(text("04 August,1975"));
        $(".table-responsive table").$(byText("Subjects")).parent().shouldHave(text(perSubject1));
        $(".table-responsive table").$(byText("Hobbies")).parent().shouldHave(text("Sports, Music"));
        $(".table-responsive table").$(byText("Picture")).parent().shouldHave(text("cat1.jpg"));
        $(".table-responsive table").$(byText("Address")).parent().shouldHave(text(currAddress));
        $(".table-responsive table").$(byText("State and City")).parent().shouldHave(text("Haryana Panipat"));
    }

}


