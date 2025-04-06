Feature: Web Form Testing
  As a user, I want to interact with all form elements and submit the form successfully.

  Background:
    Given I open the web form page
    Then I should see the page title "Web form"

  Scenario: Fill and submit the web form
    When I enter "testuser" in the text input field
    And I enter "securePass123" in the password input field
    And I enter "This is a sample text area input." in the text area
    And I select "Two" from the dropdown
    And I choose "Seattle" from the datalist dropdown
    And I upload the file "testfile.txt"
    And I check the checkbox
    And I uncheck the other checkbox
    And I click the default radio button
    And I pick the color "#ff5733" from the color picker
    And I select "04/05/2025" from the date picker
    And I adjust the slider to "75%"
    And I click the submit button
    Then I should see a confirmation message "Received!"
