package com.course.rest_api_basics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rest_api_basics.dao.BookRepository;
import com.course.rest_api_basics.model.Book;

@Service
public class BookService {
	 
//	private static List<Book> books = new ArrayList<>();
//	
//	static {
//		
//		books.add(new Book(101, "Rich dad Poor dad", "Robert Kiyosaki"));
//		books.add(new Book(102, "Compound Effect", "Darren Hardy"));
//		books.add(new Book(103, "Art of war", "Sun Tzu"));
//		
//	}
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		
		List<Book> books = (List<Book>) this.bookRepository.findAll();
		
		return books;
		
	}
	
	public Book getBookById(int id) {
		
		try {
		
			// return books.stream().filter(book -> book.getBookId() == id).findFirst().get();
			return this.bookRepository.findById(id);
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	
	public Book addBook(Book book) {
		
		Book result = this.bookRepository.save(book);
		
		return result;
		
	}
	
	public void deleteBook(Book book) {
		
		this.bookRepository.delete(book);
		
	}

	public void updateBook(Book updatedBook, int id) {
		
		updatedBook.setBookId(id);		
		this.bookRepository.save(updatedBook);
		
//		books = books.stream().map(book -> {
//			
//			if(book.getBookId() == id) {
//				
//				book.setBookTitle(updatedBook.getBookTitle());
//				book.setBookId(updatedBook.getBookId());
//				book.setAuthor(updatedBook.getAuthor());
//			
//			}
//			
//			return book;
//			
//		}).collect(Collectors.toList());
		
	}
	
}
