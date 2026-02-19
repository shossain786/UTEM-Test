package com.utem.stepdefs;

import com.utem.base.BaseTest;
import com.utem.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Given("I am on the Selenium Practice Hub homepage")
    public void open_homepage() {
        System.out.println("I am on the Selenium Practice Hub homepage");
    }

    @Given("I am on the Login form")
    public void open_login_form() {
        loginPage.openSite();
    }

    @When("I click on Forms")
    public void click_on_forms() {
        // Click on Forms link/button - adjust selector as needed
        loginPage.clickForms();
    }

    @When("I enter {string} as username")
    public void enter_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter {string} as password")
    public void enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on Login")
    public void click_login() {
        loginPage.clickLogin();
    }

    @When("I leave username blank")
    public void leave_username_blank() {
        // Step to ensure username field is empty - no action needed
    }

    @When("I leave password blank")
    public void leave_password_blank() {
        // Step to ensure password field is empty - no action needed
    }

    @Then("I should see a successful login or next page")
    public void verify_successful_login() {
        loginPage.verifySuccessfulLogin();
    }

    @Then("I should see validation errors for both fields")
    public void verify_validation_errors() {
        loginPage.verifyValidationErrors();
    }

    @When("I navigate to the login form")
    public void navigate_to_login() {
        // assume already on page for now
    }

    @When("I enter username {string} and password {string}")
    public void enter_credentials(String user, String pass) {
        loginPage.login(user, pass);
    }


    @Then("login should be successful")
    public void verify_login() {
        // assertion placeholder
    }

}
