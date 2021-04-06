Feature: Manage rules

  Background: : Create Items scenario
    Given BO user wants to create items with the following attributes
      | name | unitPrice |
      | A    | 1000.0    |
      | B    | 1100.0    |
      | C    | 1200.0    |
      | D    | 1400.0    |

    When the BO user saves new items
    Then the save is SUCCESSFUL
    And save item response into context with key "items"


  Scenario: Create and Get Rule scenario
    Given BO user wants to create rules with the following attributes
      | quantity | price  |
      | 2        | 1900.0 |
    When the BO user saves new rule of item 1 of context "items"
    Then the save is SUCCESSFUL
    And BO user retrieves rules with the following attributes
      | quantity | price  |
      | 2        | 1900.0 |
    When the BO user wants to get rules of item 1 of context "items"
    Then the get is SUCCESSFUL
    And BO user retrieves rules with the following attributes
      | quantity | price  |
      | 2        | 1900.0 |

  Scenario: Delete ALL rules scenario first get all then delete all)
    When the BO user wants to get rules of item 1 of context "items"
    And the get is SUCCESSFUL
    And save rules response into payload
    And the BO user wants to delete all rules of item 1 of context "items"
    Then the delete is SUCCESSFUL
    And the BO user wants to get rules of item 1 of context "items"
    And the get is SUCCESSFUL
    And BO user retrieves rules with the following attributes
      | quantity | price |
