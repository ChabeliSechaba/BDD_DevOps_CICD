
Feature:Booking
  A feature to book the hotel

  Background: Login successful
    Given a user is on the home page
    When  a user navigates to the Login page using "http://adactinhotelapp.com/"
    And a user enter "AkinAfrika" and "0G5C0D"
    And a user clicks the login button
    Then a user has login successfully

  Scenario Outline: Book Hotel successfully
    Given a user fill in the booking form
    And a user enters "<firstname>", "<lastname>", "<bill-address>", "<card-number>", "<cvv-number>"
    Then the hotel was booked successfully

    Examples:
      |firstname| lastname| bill-address| card-number| cvv-number|
      | Mister  | Alibaba |  1236 Alostro Street, Tshwane, Pitori, 0001  | 1245451214549126  |  124 |



