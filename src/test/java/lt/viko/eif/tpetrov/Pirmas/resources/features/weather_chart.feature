Feature: Hourly weather chart

  Scenario Outline: Hourly chart appears at bottom after city search
    Given the user opens the weather page
    When the user searches for "<city>"
    Then the hourly chart should be visible at the bottom

    Examples:
      | city    |
      | Vilnius |
      | Kaunas  |
      | London  |
      | Paris   |
