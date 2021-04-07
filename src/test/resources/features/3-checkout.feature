Feature: Checkout Shopping cart

  Background: : Create Items scenario
    Given the BO user wants to get items
    And the get is SUCCESSFUL
    And save item response into payload
    And the BO user wants to delete items
    And the delete is SUCCESSFUL
    And BO user wants to create items with the following attributes
      | name | unitPrice |
      | A    | 50.0      |
      | B    | 30.0      |
      | C    | 20.0      |
      | D    | 15.0      |

    And the BO user saves new items
    And the save is SUCCESSFUL
    And save item response into context with key "items"
    When BO user wants to create rules with the following attributes
      | quantity | price |
      | 3        | 130.0 |
    And the BO user saves new rule of item 1 of context "items"
    Then the save is SUCCESSFUL
    And BO user wants to create rules with the following attributes
      | quantity | price |
      | 2        | 45.0  |
    And the BO user saves new rule of item 2 of context "items"
    And the save is SUCCESSFUL


  Scenario: Create new Shopping cart with 3 items 'A'
    Given user wants to reset shopping cart
    And the delete is SUCCESSFUL
    And user wants to add new item to cart of item 1 of context "items"
    When the save is SUCCESSFUL
    And user wants to add new item to cart of item 1 of context "items"
    And the save is SUCCESSFUL
    And user wants to add new item to cart of item 1 of context "items"
    And the save is SUCCESSFUL
    And user wants to get the shopping-cart
    Then the get is SUCCESSFUL
    And the total price must be 130.0
    And Item types of Shopping cart must equals 1
    And quantity of item 1 of context "items" in cart must be 3

  Scenario: Create new Shopping cart with 3 items 'B'
    Given user wants to reset shopping cart
    And the delete is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    When the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    And the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    And the save is SUCCESSFUL
    And user wants to get the shopping-cart
    Then the get is SUCCESSFUL
    And the total price must be 75.0
    And Item types of Shopping cart must equals 1
    And quantity of item 2 of context "items" in cart must be 3

  Scenario: Create new Shopping cart with 3 items 'B' and One 'A'
    Given user wants to reset shopping cart
    And the delete is SUCCESSFUL
    And user wants to add new item to cart of item 1 of context "items"
    When the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    When the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    And the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    And the save is SUCCESSFUL
    And user wants to get the shopping-cart
    Then the get is SUCCESSFUL
    And the total price must be 125.0
    And Item types of Shopping cart must equals 2
    And quantity of item 1 of context "items" in cart must be 1
    And quantity of item 2 of context "items" in cart must be 3

  Scenario: Create new Shopping cart with 3 items 'B'
    Given user wants to reset shopping cart
    And the delete is SUCCESSFUL
    And user wants to add new item to cart of item 1 of context "items"
    And the save is SUCCESSFUL
    And user wants to remove item from cart of item 1 of context "items"
    And the delete is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    When the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    And the save is SUCCESSFUL
    And user wants to add new item to cart of item 2 of context "items"
    And the save is SUCCESSFUL
    And user wants to get the shopping-cart
    Then the get is SUCCESSFUL
    And the total price must be 75.0
    And Item types of Shopping cart must equals 1
    And quantity of item 2 of context "items" in cart must be 3


