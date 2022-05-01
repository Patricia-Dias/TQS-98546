Feature: Searching Covid Information in Covid Data site

    Scenario: Search for statistics in all countries
        When I navigate to "http://localhost:8083"
        And I click Check Statistics
        Then I should be able to see a table
    
    Scenario: Search statistics for one country
        When I navigate to "http://localhost:8083"
        And I click Check Statistics
        And I insert country "uk"
        And I click Search
        Then I should be able to see a table with data related to "UK"

    Scenario: Search history of statistics for one country
        When I navigate to "http://localhost:8083"
        And I click Check History
        And I insert country "uk"
        And I click Search
        Then I should be able to see a table with data related to "UK"
    
    Scenario: Search history of statistics for one country and a day
        When I navigate to "http://localhost:8083"
        And I click Check History
        And I insert country "uk"
        And I insert date "2022-02-06"
        And I click Search
        Then I should be able to see a table with data related to "UK" from "2022-02-06"

        