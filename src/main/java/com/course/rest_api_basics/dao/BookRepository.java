package com.course.rest_api_basics.dao;

import org.springframework.data.repository.CrudRepository;

import com.course.rest_api_basics.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	
	public Book findById(int id);
	

}
