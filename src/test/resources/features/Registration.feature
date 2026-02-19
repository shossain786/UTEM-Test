Feature: Registration validation

  Scenario: User can fill registration form inputs
    Given I am on Registration Form
    When I enter "Saddam" into First Name
    And  I enter "saddam@in.com" into Email
    And  I select "India" as Country
    And  I click on registration button
    Then I should see alert with message "Registration form submitted successfully!"
