package io.cucumber.skeleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.skeleton.Book;

public class Library {
    private final List<Book> store = new ArrayList<>();
 
	public void addBook(final Book book) {
		store.add(book);
	}
 
	public List<Book> findBooks(final LocalDateTime from, final LocalDateTime to) {
		Calendar end = Calendar.getInstance();
		end.setTime(getUtilDate(to));
		end.roll(Calendar.YEAR, 1);
 
		return store.stream().filter(book -> {
			return getUtilDate(from).before(getUtilDate(book.getPublished())) && end.getTime().after(getUtilDate(book.getPublished()));
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}

	private Date getUtilDate(LocalDateTime local){
		return Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
	}

    public List<Book> findBooksByAuthor(String author) {
		List<Book> res = new ArrayList<>();
        for (Book b : store)
			if (b.getAuthor().equals(author))
				res.add(b);
		return res;
    }

    public List<Book> findBooksByCategory(String category) {
        List<Book> res = new ArrayList<>();
        for (Book b : store){
			System.out.println(b.getCategory() +" \t equals? \t"+category);
			System.out.println(b.getCategory().equalsIgnoreCase(category));
			if (b.getCategory().equalsIgnoreCase(category))
				res.add(b);
		}
		return res;
    }

	@Override
	public String toString(){
		String res = "";
		for (Book book : this.store){
			res += book.getTitle()+"\t";
		}
		return res;
	}
}
