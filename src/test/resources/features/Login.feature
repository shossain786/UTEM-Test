Feature: Login feature testing

  Scenario: User can login with valid credentials
    Given I am on the Selenium Practice Hub homepage
    When I click on Forms
    And I enter "admin" as username
    And I enter "password" as password
    And I click on Login
    Then I should see a successful login or next page

  Scenario: Login form shows error when fields are blank
    Given I am on the Login form
    When I leave username blank
    And I leave password blank
    And I click on Login
    Then I should see validation errors for both fields
