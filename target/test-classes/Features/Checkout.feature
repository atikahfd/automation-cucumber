Feature: Checkout
  Background:
    Given User already add product to cart

  Scenario: Successfully input user's information
    When User clicks on checkout button
    And User inputs First name "John"
    And User inputs Last name "Doe"
    And User inputs postal code "10000"
    And User clicks on continue button
    Then Checkout overview page shown

  Scenario: Cannot proceed without inputting user information
    When User clicks on checkout button
    And User clicks on continue button
    Then Alert error appears