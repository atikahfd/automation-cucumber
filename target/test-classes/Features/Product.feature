Feature: Product
  Background:
    Given User is logged in to the application
  Scenario: Add product to a cart
    When User clicks on Add to cart button
    Then Number on cart icon is added to 1