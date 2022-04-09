Feature: Login feature

  Background:
    #Given user is navigated to HRMS application

  @smoke @batch11
  Scenario Outline: Valid admin login
    When user enters different "<username>" and "<password>"
    And user clicks on login button
    Then "<admin>" user is successfully logged in
    Examples:
      |username|password|admin|
      |Admin   |Hum@nhrm123     |Admin|


  @regression @smoke @errorOne @mvn
  Scenario: Valid ess login
    When user enters valid ess username and password
    And user clicks on login button
    Then admin user is successfully logged in

  @login
  Scenario Outline: negative login test
    When user enters different "<username>" and "<password>" and verify the "<error>" for all the combinations
    Examples:
    |username|password|error|
    |Admin   |xyz     |Invalid credentials|
    |cristiano|Hum@nhrm123|Invalid credentials|
    |         |Hum@nhrm123|Username cannot be empty|
    |Admin    |           |Password cannot be empty|











