Feature:  verify object functionality working as per requirement.

Scenario: Verify create Object
Given  An "CreateObject"  payload
When the user calls the "createObject" API with "post" method
Then the Object operation is successful with status code 200

Scenario: Verify update Object by id
Given an "UpdateObject" payload
When the user calls the "updateObject" API with "put" method
Then the Object operation is successful with status code 200

Scenario:  Verify Partial update Object by id
  Given an "PartialUpdateObject" payload
  When the user calls the "PartialupdateObject" API with "patch" method
  Then the Object operation is successful with status code 200

  Scenario:  Verify  Delete exiting Object by id
    Given exiting Object by id
    When the user calls the "DeleteObject" API with "Delete" method
    Then the Object operation is successful with status code 200