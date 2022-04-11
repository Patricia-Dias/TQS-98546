package io.cucumber.skeleton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

//dates = ([0-9]{4})-([0-9]{2})-([1-9]{2})
public class BookSearchSteps {
    Library library = new Library();
	List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDateTime iso8601Date(String year, String month, String day){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
	}

	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}, with the category {string}")
	public void addNewBook(final String title, final String author, final LocalDateTime published, final String category) {
		Book book = new Book(title, author, published, category);
		library.addBook(book);
	}
 
	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
		result = library.findBooks(from, to);
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertEquals(result.size(), booksFound);
		// assertThat(result.size(), equalTo(booksFound));
	}

	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertEquals(result.get(position-1).getTitle(), title);
		// assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

	@When("the costumer searches for books written by {string}")
	public void the_costumer_searches_for_books_written_by(String author) {
		// Write code here that turns the phrase above into concrete actions
		result = library.findBooksByAuthor(author);
	}

	@When("the costumer searches for books by category {string}")
	public void the_costumer_searches_for_books_with_category(String category) {
		// Write code here that turns the phrase above into concrete actions
    	result = library.findBooksByCategory(category);
	}
}
