Feature: Dashboard functionality

  @dashboard
  Scenario: Verify dashboard tabs
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    Then verify all the dashboard tabs
    |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|