package com.course.rest_api_basics.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.rest_api_basics.model.Book;
import com.course.rest_api_basics.service.BookService;

@RestController
public class ControllerOne {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/get-book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		
		Book book = bookService.getBookById(id);
		
		if(book == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.of(Optional.of(book));
		
	}
	
	@GetMapping("/get-books")
	public ResponseEntity<List<Book>> getAllBooks() {
		
		List<Book> books = bookService.getAllBooks();
		
		if(books.size() <= 0) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		
		return ResponseEntity.of(Optional.of(books));
		
	}
	
	@PostMapping("/post-book")
	public ResponseEntity<Book> postBook(@RequestBody Book book) {
		
		try {
			
			Book addedBook = bookService.addBook(book);
		
			return ResponseEntity.of(Optional.of(addedBook));
		
		} catch(Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	@DeleteMapping("/delete-book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		
		try {
			
			Book book = bookService.getBookById(id);
			bookService.deleteBook(book);
		
			return ResponseEntity.ok().build();
		
		} catch(Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	@PutMapping("/update-book/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id) {
		
		try {
		
			bookService.updateBook(book, id);
			Book updatedBook = bookService.getBookById(book.getBookId());
			
			return ResponseEntity.of(Optional.of(updatedBook));
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
		
	}
	
	
}
