Feature: Cart Functionality

  Scenario Outline: Check if "Add to cart" adds item to the cart
    Given User is on the home page
    When Clicks on the Add to cart button for "<item>"
    Then User should navigate to the cart Page
    And "<item>" should be present in the cart
    And Close the browser

    Examples:
      | item                  |
      | Sauce Labs Backpack   |

