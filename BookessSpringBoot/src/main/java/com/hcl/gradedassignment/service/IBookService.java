package com.hcl.gradedassignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.gradedassignment.beans.Book;
import com.hcl.gradedassignment.exception.IdNotFoundException;

@Service
public interface IBookService {

    public Book addBook(Book book) ;
	
	public List<Book> displayBooks();
	
	public String deleteBook(int id) throws IdNotFoundException;
	
	public Book updateBook(Book book, int id) throws IdNotFoundException;

	public Book searchBook(int id) throws IdNotFoundException ;
	
	
}
