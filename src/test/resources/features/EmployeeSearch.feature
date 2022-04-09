Feature: US-12345 Employee search
  Background:
    When user is logged in with valid admin credentials
    #When user enters valid admin username and password
    #And user clicks on login button
    #Then admin user is successfully logged in
    When user navigates to employee list page

  @test
  Scenario: Search employee by id
    When user enters valid employee id
    And user clicks on search button
    Then user see employee information is displayed

   @test1
  Scenario: Search employee by name
    When user enters valid employee name
    And user clicks on search button
    Then user see employee information is displayed




