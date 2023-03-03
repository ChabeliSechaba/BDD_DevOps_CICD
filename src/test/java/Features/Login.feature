
Feature: Login
  A feature to test a login to a application
  @Login
  Scenario Outline: Login successful
    Given a user is on the home page
    When  a user navigates to the Login page using "<URL>"
    And a user enter "<username>" and "<password>"
    And a user clicks the login button
    Then a user has login successfully

    Examples:
    |URL|username|password|
    | http://adactinhotelapp.com/ |  AkinAfrika | 0G5C0D  |