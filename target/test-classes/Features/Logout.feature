Feature: Logout
  Background:
    Given User logged in to the application
  Scenario: Logout success
    When User clicks on logout button
    Then Login page shown