Feature: City movies advanced

  Scenario: Get locations
    Given the apis are up and running for "http://cmapi.bananaappscenter.com/"
    When a user performs a GET request to "api/Location/LocationDetails"
    Then the request code should be 200
    And I should see json response with pairs on the filtered "Msg" node
      | Message    | Success Location Details |
      | StatusCode | 200                      |
      | isError    | false                    |
      | isSuccess  | true                     |