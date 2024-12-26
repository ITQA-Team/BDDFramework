Feature: Filter functionality on Home Page

  Scenario Outline: Verify sorting products using filter
    Given User is on the home page
    When User selects "<filterOption>" from the filter dropdown
    Then Products should be sorted according to "<filterOption>"
    And Close the browser

  Examples:
    | filterOption           |
    | Name (A to Z)          |
    | Name (Z to A)          |
    | Price (low to high)    |
    | Price (high to low)    |