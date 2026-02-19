package com.utem.stepdefs;

import com.utem.base.BaseTest;
import com.utem.pages.RegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class RegistrationSteps extends BaseTest {
    RegistrationPage registrationPage =  new RegistrationPage();


    @Given("I am on Registration Form")
    public void i_am_on_registration_form() {
        System.out.println("I am on Registration Form");
    }

    @When("I enter {string} into First Name")
    public void i_enter_into_first_name(String string) {
        registrationPage.enterFirstName(string);
    }

    @When("I enter {string} into Email")
    public void i_enter_into_email(String string) {
        registrationPage.enterEmail(string);
    }

    @When("I select {string} as Country")
    public void i_select_as_country(String string) {
        registrationPage.selectCountry(string);
    }

    @Then("I should see alert with message {string}")
    public void i_should_see_alert_with_message(String string) {
        String actualMessage = registrationPage.getAlertMessage();
        Assertions.assertEquals(actualMessage, string);
    }

    @And("I click on registration button")
    public void iClickOnRegistrationButton() {
        registrationPage.clickRegisterBtn();
    }
}
