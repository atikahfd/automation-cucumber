Feature: Cart
  Background:
    Given User already logged in to the application
  Scenario: Go to your cart page with added product
    When User adds one product to cart
    And User clicks on cart icon
    Then Your cart page shown


  Scenario: Go to checkout-your information page
    When User adds one product to cart
    And User clicks on cart icon
    And User clicks on Checkout button
    Then Checkout page shown


