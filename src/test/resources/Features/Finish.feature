Feature: Finish
  Background:
    Given User already fill users information

  Scenario: Successfully finish purchasing
    When User clicks on Finish button
    Then Checkout complete page shown

  Scenario: Back to products page
    Given User clicks on Finish button
    When User clicks on Back Home page
    Then Redirected to Products page