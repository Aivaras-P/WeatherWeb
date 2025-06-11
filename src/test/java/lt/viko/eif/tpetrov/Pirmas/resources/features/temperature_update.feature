Feature: Temperature display

  Scenario Outline: Temperature and related data appears after city search
    Given the user opens the weather page
    When the user searches for "<city>"
    Then the temperature value should be visible and not "--"
    And the feels like temperature should be visible
    And the current weather description should be visible
    And the wind speed should be visible
    And the sunrise time should be visible
    And the sunset time should be visible
    And the air quality should be visible

    Examples:
      | city    |
      | Vilnius |
      | Kaunas  |
      | London  |
      | Paris   |
