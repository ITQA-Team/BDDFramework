Feature: Verify functionality of the side menu

  Scenario Outline: Verify opening, navigating, and closing the side menu.

    Given the user is on the homepage
    When the user clicks on the "Open Menu" button
    Then the side menu should be visible
    When the user clicks on a menu item "<menuItem>"
    Then the user should be redirected to the respective page or reset the app state
    When the user clicks on the "Close Menu" button
    Then the side menu should be closed
    And the browser should be closed

    Examples:
      | menuItem            |
      | All Items           |
      | About               |
      | Logout              |
      | Reset App State     |
