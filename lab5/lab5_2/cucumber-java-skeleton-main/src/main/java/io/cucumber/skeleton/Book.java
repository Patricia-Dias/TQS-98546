package io.cucumber.skeleton;

import java.time.LocalDateTime;

public class Book {

    private String title;
	private String author;
	private LocalDateTime published;
	private String category;

	public Book(String title, String author, LocalDateTime published2, String category){
		title = this.title;
		author = this.author;
		published2 = this.published;
		category = this.category;
	}

	public LocalDateTime getPublished() {
		return published;
	}

    public String getTitle() {
        return title;
    };

	public String getAuthor(){
		return author;
	};

	public String getCategory(){
		return category;
	};

}
