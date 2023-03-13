@Booking
Feature:Booking
  A feature to book the hotel

  Background: Login successful
    Given a user is on the home page
    When  a user navigates to the Login page using "http://adactinhotelapp.com/"
    And a user enter "AkinAfrika" and "0G5C0D"
    And a user clicks the login button
    Then a user has login successfully
    
  Scenario Outline: Book Hotel successfully and unsuccessful booking
    Given a user fill in the booking form
    And a user enters "<firstname>", "<lastname>", "<bill-address>", "<card-number>", "<cvv-number>"
    Then the hotel was booked successfully and unsuccessful booking

    Examples:
      | firstname | lastname | bill-address                                           | card-number      | cvv-number |
      | Mister    | Alibaba  | 1236 Alostro Street, Tshwane, Pitori, 0001             | 1245451214549126 | 124        |
      | Jackie    | Chan     | 322 Is_not_make_sure Street, Orlando West, Sotra, 1632 | 12454512145      | 127        |


