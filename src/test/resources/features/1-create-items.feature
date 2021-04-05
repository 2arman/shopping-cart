Feature: Manage Items

  Scenario: Create Items scenario
    Given BO user wants to create items with the following attributes
      | name | unitPrice |
      | A    | 1000.0    |
      | B    | 1100.0    |
      | C    | 1200.0    |
      | D    | 1400.0    |

    When the BO user saves new items
    Then the save is SUCCESSFUL

  Scenario: Get ALL Items scenario
    When the BO user wants to get items
    Then the get is SUCCESSFUL
    And BO user retrieves items with the following attributes
      | name | unitPrice |
      | A    | 1000.0    |
      | B    | 1100.0    |
      | C    | 1200.0    |
      | D    | 1400.0    |

  Scenario: Delete ALL Items scenario
    Given BO user wants to create items with the following attributes
      | name | unitPrice |
      | A    | 1000.0    |
      | B    | 1100.0    |
      | C    | 1200.0    |
      | D    | 1400.0    |

    When the BO user wants to delete items
    And the delete is SUCCESSFUL
    Then the BO user wants to get items
    And the get is SUCCESSFUL
    And BO user retrieves items with the following attributes
      | name | unitPrice |
