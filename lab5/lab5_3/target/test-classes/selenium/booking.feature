Feature: Booking Flight in Blazedemo site

    Scenario: Sucessfull booking
        When I navigate to "https://blazedemo.com"
        And I select a flight from 'Portland' to 'Berlin'
        And I click Find Flights
        And I click Choose Flight
        And I write 'zdxfcv' in 'inputName'
        And I click Purchase Flights
        Then I should be able to see the receipt 'BlazeDemo Confirmation'