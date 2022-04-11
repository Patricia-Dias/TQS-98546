Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in '2013-03-14', with the category 'Romace'
      And a book with the title 'Some other book', written by 'Tim Tomson', published '2014-08-23', with the category 'Romace'
      And a book with the title 'How to cook a dino', written by 'Fred Flintstone', published in '2012-01-01', with the category 'Sci-Fi'
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'
    When the costumer searches for books written by 'Fred Flintstone'
    Then 1 book should have been found
      And Book 1 should have the title 'How to cook a dino'
    When the costumer searches for books by category 'Romance'
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'

  # Scenario: Search books by author
  #   Given a book with the title 'One good book', written by 'Anonymous', published in '2013-03-14', with the category 'Romace'
  #     And another book with the title 'Some other book', written by 'Tim Tomson', published '2014-08-23', with the category 'Romace'
  #     And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in '2012-01-01', with the category 'Sci-Fi'
  #   When the costumer searches for books written by 'Fred Flintstone'
  #   Then 1 book should have been found
  #     And Book 1 should have the title 'How to cook a dino'

  # Scenario: Search books by category
  #   Given a book with the title 'One good book', written by 'Anonymous', published in '2013-03-14', with the category 'Romace'
  #     And another book with the title 'Some other book', written by 'Tim Tomson', published '2014-08-23', with the category 'Romace'
  #     And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in '2012-01-01', with the category 'Sci-Fi'
  #   When the costumer searches for books by category 'Romance'
  #   Then 2 books should have been found
  #     And Book 1 should have the title 'Some other book'
  #     And Book 2 should have the title 'One good book'