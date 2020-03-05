@login
Feature: create new account and add dress to the cart

  Scenario: create new user, verify account is created successfully and added dress to cart
	Given I am on My Store form
	When I click on Sign in option
	And I create an account with following personal information to register
	  | Title        | Mr              |
	  | FirstName    | Siva            |
	  | LastName     | Sirigiri        |
	  | Password     | password        |
	  | AddressLine1 | 47 Hartford St. |
	  | City         | San Diego       |
	  | State        | California      |
	  | PostCode     | 92083           |
	  | Country      | United States   |
	  | MobilePhone  | 0123456788      |
	And I should be able to see account has been created successfully
	When I find the most expensive dress and add it to the cart
	And I logout from the application
	When I login back to my store portal using above created user
	Then I should be able to login successfully
  	And I should see that added item in the cart is still available