@Searching
Feature:Search
  A feature to search the itinerary

  Background: Search booked hotel successfully
    Given a user is on the home page
    When  a user navigates to the Login page using "http://adactinhotelapp.com/"
    And a user enter "AkinAfrika" and "0G5C0D"
    And a user clicks the login button
    Then a user has login successfully

  Scenario: Search hotel was booked successfully
    Given a user fill in the booking form
    And a user enters "Mister", "Alibaba", "1236 Alostro Street, Tshwane, Pitori, 0001", "1245451214549126", "124"
    Then the hotel was booked successfully
    And a user clicks on search hotel link
    And a user enter order number and click on the go button
    Then the search is successful