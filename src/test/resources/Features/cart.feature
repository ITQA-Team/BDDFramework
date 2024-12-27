Feature: Cart Functionality

#  //Tehan-Add to cart feature test
  Scenario Outline: Check if "Add to cart" adds item to the cart
    Given User is on the home page
    When Clicks on the Add to cart button for "<item>"
    Then User should navigate to the cart Page
    And "<item>" should be present in the cart
#    And Close the browser

    Examples:
      | item                  |
      | Sauce Labs Backpack   |

#  //Tehan-remove from cart feature test
  Scenario Outline: Check if "Remove from cart" removes item from the cart
    Given User is on the home page
    When Clicks on the Add to cart button for "<item>"
    And Navigates to the cart Page
    When Clicks on the Remove from cart button for "<item>"
    Then "<item>" should not be present in the cart
#    And Close the browser

    Examples:
      | item                |
      | Sauce Labs Backpack |


#  //Tehan-checkout page
  Scenario Outline: Check if user can navigate to the checkout page
    Given User is on the home page
    When Clicks on the Add to cart button for "<item>"
    And Navigates to the cart Page
    And Clicks on the Checkout button
    Then User should navigate to the Checkout Page
#    And Close the browser

    Examples:
      | item                |
      | Sauce Labs Backpack |

    #  //Malinsha - item description page
  Scenario Outline: Verify that clicking on the item's image redirects to the same detailed page
    Given User is on the home page
    When User clicks on the image of "<item>"
    Then User should be redirected to the detailed page of "<item>"

    Examples:
      | item                |
      | Sauce Labs Backpack |

 # //Malinsha - item name description page
  Scenario Outline: Verify that clicking on the item's name redirects to the detailed description page
    Given User is on the home page
    When User clicks on the item name of "<item>"
    Then User should be redirected to the detailed description page of "<item>"

    Examples:
      | item                |
      | Sauce Labs Backpack |
