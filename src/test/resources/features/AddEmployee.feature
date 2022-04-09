Feature: Add employees

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add employee button

  @dropdown
  Scenario: Adding one employee from feature file
    And user enters firstname and lastname
    And user clicks on save button
    Then employee added successfully

  @ddt
  Scenario Outline: Add employee
    And user enters "<firstName>" "<middleName>" and "<lastName>"
    And user clicks on save button
    Then employee added successfully

    Examples:
    |firstName|middleName|lastName|
    |test123  |MS        |test456 |
    |testabc  |MS        |testxyz |
    |test987  |MS        |test321 |

  @sampleCucumberScenario
  Scenario: Adding one employee using cucumber feature
    And user enters direct data "sebastianfn" "christinemn" and "yogitaln"
    And user clicks on save button
    Then employee added successfully

  @datatable
  Scenario: Add employee
    When user add multiple employees and verify they are added
    |firstName|middleName|lastName|
    |test123  |MS        |test456 |
    |testabc  |MS        |testxyz |
    |test987  |MS        |test321 |

   @excel
   Scenario: Adding employees from excel file
    When user adds multiple employees from excel file using "EmployeeData" sheet and verify the added employee

  @db
  Scenario: Front-end and Back-end Testing
    When user enters direct data "Sorbon" "K" and "Imomnazar"
    And capture the employee id
    And user clicks on save button
    And employee added successfully
    And query the information from backend
    Then verify the results from UI and backend