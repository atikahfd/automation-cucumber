Feature: Login
  Scenario: Login standard user success
    Given User opens saucedemo website
    When User inputs username "standard_user"
    And User inputs password "secret_sauce"
    And User clicks on login button
    Then Products page shown

  Scenario: Login user locked failed
    Given User opens saucedemo website
    When User inputs locked username "locked_out_user"
    And User inputs password "secret_sauce"
    And User clicks on login button
    Then Alert error shown