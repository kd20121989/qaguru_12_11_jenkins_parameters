package tests.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("prop_and_owner")
public class PracticeFormWIthPropertiesAndOwner extends TestBase {

    @Test
    @DisplayName("Successfully fill and apply registration form")
    void fillStudentRegistrationForm() {
        String firstName = "Dmitrii";
        String lastName = "Kudriashov";
        String userEmail = "somemail@google.com";
        String gender = "Male";
        String phoneNumber = "9675915303";
        String yearOfBirth = "1989";
        String monthOfBirth = "December";
        String dayOfBirth = "20";
        String dateOfBirth = dayOfBirth + " " + monthOfBirth + "," + yearOfBirth;
        String subjectEng = "English";
        String subjectMath = "Maths";
        String subjectCompSci = "Computer Science";
        String hobbyRead = "Reading";
        String hobbyMusic = "Music";
        String pictureFile = "img/photo_2020-02-25_11-37-04 - Copy.jpg";
        String currentAddress = "13 Lightning Point Drive, Memphis";
        String stateUttar = "Uttar Pradesh";
        String cityLucknow = "Lucknow";

        step("Open registration form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        step("Fill registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $(byTagAndText("label", gender)).click();
            $("#userNumber").setValue(phoneNumber);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(String.format
                    (".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", dayOfBirth)).click();
            $("#subjectsInput").setValue(subjectMath.substring(0, 1)).pressEnter()
                    .setValue(subjectEng.substring(0, 1)).pressEnter()
                    .setValue(subjectCompSci.substring(0, 3)).pressEnter();
            $("#hobbiesWrapper").$(byTagAndText("label", hobbyRead)).click();
            $("#hobbiesWrapper").$(byTagAndText("label", hobbyMusic)).click();
            $("#uploadPicture").uploadFromClasspath(pictureFile);
            $("#currentAddress").setValue(currentAddress);
            $("#state").scrollIntoView(true).click();
            $("#state").$(byTagAndText("div", stateUttar)).click();
            $("#city").click();
            $("#city").$(byTagAndText("div", cityLucknow)).click();
            $("#submit").click();
        });

        step("Verify resulted data table", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").$(byText("Student Name"))
                    .sibling(0).shouldHave(text(String.format("%s %s", firstName, lastName)));
            $(".table-responsive").$(byText("Student Email"))
                    .sibling(0).shouldHave(text(userEmail));
            $(".table-responsive").$(byText("Gender"))
                    .sibling(0).shouldHave(text(gender));
            $(".table-responsive").$(byText("Mobile"))
                    .sibling(0).shouldHave(text(phoneNumber));
            $(".table-responsive").$(byText("Date of Birth"))
                    .sibling(0).shouldHave(text(dateOfBirth));
            $(".table-responsive").$(byText("Subjects"))
                    .sibling(0).shouldHave(text(
                    String.join(", ", subjectMath, subjectEng, subjectCompSci)));
            $(".table-responsive").$(byText("Hobbies"))
                    .sibling(0).shouldHave(text(
                    String.join(", ", hobbyRead, hobbyMusic)));
            $(".table-responsive").$(byText("Picture"))
                    .sibling(0).shouldHave(text(pictureFile.substring(4)));
            $(".table-responsive").$(byText("Address"))
                    .sibling(0).shouldHave(text(currentAddress));
            $(".table-responsive").$(byText("State and City"))
                    .sibling(0).shouldHave(text(stateUttar + " " + cityLucknow));
        });
    }
}
