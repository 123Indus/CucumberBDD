Feature: Login

  Scenario: Successful login with valid credentials
    Given User launch chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and Password as "admin"
    And click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on logout link
    Then page title should be "Your store. Login"
    And close browser

  @sanity
  Scenario Outline: Successful login with valid credentials DDT
    Given User launch chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "<email>" and Password as "<password>"
    And click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on logout link
    Then page title should be "Your store. Login"
    And close browser

    Examples: 
      | email               | password |
      | admin@yourstore.com | admin    |
      | test@yourstore.com  | admin    |
