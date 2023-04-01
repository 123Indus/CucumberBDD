#Author: Subir Sarkar
Feature: Customer


   Background: Steps Common for all Scenarios
    Given User launch chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and Password as "admin"
    And click on login
    Then User can view Dashboard
    

  @AddCustomer @sanity
  Scenario: Add new Customer
    When User click on customers Menu
    And Click on Customers Menu item
    And Click on Add new button
    Then User can view add new customer page
    When user enters customer info
    And click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

  @searchEmail @sanity
  Scenario: Search Customer by Email
    Then User can view Dashboard
    When User click on customers Menu
    And Click on Customers Menu item
    And Enter Customer Email
    When Click on Search button
    Then User should found Email in the Search table
    And close browser

  @SearchName @regression
  Scenario: Search customer by name
    When User click on customers Menu
    And Click on Customers Menu item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on Search1 button
    Then User should found Name in the Search table
    And close browser
