Feature: Verify object functionality working as per requirement.

  Scenario Outline: Verify Object operations
    Given an "<payloadType>" payload
    When the user calls the API with "<method>" method
    Then the Object operation is successful with status code <statusCode>

    Examples:
      | payloadType         | method | statusCode |
      | CreateObject        | post   | 200        |
      | UpdateObject        | put    | 200        |
      | PartialUpdateObject | patch  | 200        |
      | DeleteObject        | delete | 200        |