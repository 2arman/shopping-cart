Feature: Checkout Shopping cart

  Background: : Create Items scenario
    Given BO user wants to create items with the following attributes
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


  Scenario: Create new Shopping cart
    Given user wants to add new item of item 1 of context "items"
    When the save is SUCCESSFUL
    And user wants to add new item of item 1 of context "items"
    And the save is SUCCESSFUL
    And user wants to add new item of item 1 of context "items"
    And the save is SUCCESSFUL
    And user wants to get the shopping-cart
    Then the get is SUCCESSFUL
    And the total price must be 130.0
    And Item types of Shopping cart must be only 1
    And quantity of item 1 of context "items" in cart must be 3



