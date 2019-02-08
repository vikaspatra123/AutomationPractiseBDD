@regresssion
Feature: User login in Automation Practise website
  As a user
  I want to see the login page
  So that I can login successfully

  @smoke @validLogin
  Scenario: User is able to login successfully using valid credentials
    Given User is on the home page
    When User selects the sign in link
    Then User should be in the login page
    When User enters email as "testaccount@mailinator.com" and password as "Password1"
    And User selects Sign in
    Then User should be logged in successfully
    And User should see his name as "john smith"


  @vikas @invalidLogin
  Scenario Outline: User can see validation message for invalid credentials
    Given User is on the home page
    When User selects the sign in link
    Then User should be in the login page
    When User enters email as "<username>" and password as "<password>"
    And User selects Sign in
    Then User should see error message as "<error_message>"

    Examples:
      | username                   | password      | error_message              |
      |                            |               | An email address required. |
      | ghfth                      |               | Invalid email address.     |
      | testaccount@mailinator.com | wrongpassword | Authentication failed.     |

  #@smoke @shoppingcart
  Scenario Outline: User is able to add items in the shopping cart
    Given User is on the home page
    When user selects the sign in link
    Then User should be in the login page
    When User enters email as "testaccount@mailinator.com" and password as "Password1"
    And User selects Sign in
    Then User should be logged in successfully
    And User should see his name as "john smith"
    When User selects T-shirt tab
    Then User will be navigated to Tshirt page
    When User selects the required Tshirt
    Then User will be navigated to the T shirt description page
    When User selects the quantity "<Quantity>" and size "<Size>"of the T Shirt
    Then User verifies quantity "<Quantity>" and size has been updated
    When User selects 'Add to cart'
    Then User will see the pop up window with the updated basket
    Examples:
    |Quantity  | Size |
    |2         | M    |


