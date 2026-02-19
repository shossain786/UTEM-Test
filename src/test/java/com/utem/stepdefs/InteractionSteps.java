package com.utem.stepdefs;

import com.utem.base.BaseTest;
import com.utem.pages.InteractionPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InteractionSteps extends BaseTest {
    InteractionPage page = new InteractionPage();

    @Given("I am on the interaction page")
    public void open_page() {
        System.out.println("I am on the interaction page");
    }

    @When("I click on the main action button")
    public void click_button() {
        page.clickActionButton();
    }

    @Then("I should see a response message")
    public void verify_response() {
        // assertion placeholder
        page.closeBrowser();
    }
}
