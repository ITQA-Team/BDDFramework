Feature: Checkout Form Functionality

  Scenario: Submit the checkout form with valid credentials
    Given User is on the checkout page
    When User enters valid first name, last name, and postal code
    And User clicks the continue button
    Then The form should be submitted successfully
    And Close the browser