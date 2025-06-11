Feature: 6-day forecast temperature display

  Scenario Outline: Temperature is shown for each of the next 6 days
    Given the user opens the weather page
    When the user searches for "<city>"
    Then the forecast temperature should be visible for each of the next 6 days
    And the average night temperature should be visible for each of the next 6 days

    Examples:
      | city    |
      | Vilnius |
      | Kaunas  |
      | London  |
      | Paris   |
