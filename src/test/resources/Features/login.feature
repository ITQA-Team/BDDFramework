Feature: Login page Automation of saucedemo App

  Scenario Outline: Check login is successful with valid credentials
    Given User is on the login page
    When User enters a valid "<username>" and "<password>"
    And Clicks on the Login button
    Then User should be navigated to the Home Page
#    And Close the browser

    Examples:
      | username | password |
      | standard_user |secret_sauce|
      |locked_out_user | secret_sauce |
      | problem_user | secret_sauce |
      | performance_glitch_user | secret_sauce |
#
#//Tehan test case 01
  Scenario Outline: Check login fails with invalid password
    Given User is on the login page
    When User enters a valid "<username>" and an invalid "<password>"
    And Clicks on the Login button
    Then User should see an error message
#    And Close the browser

    Examples:
      | username             | password        |
      | standard_user        | wrong_password  |
      | locked_out_user      | invalid_pass    |
      | problem_user         | wrong_pass      |

