package com.hcl.gradedassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.hcl.gradedassignment.beans.Book;
import com.hcl.gradedassignment.beans.LikedBook;
import com.hcl.gradedassignment.exception.IdNotFoundException;
import com.hcl.gradedassignment.service.IBookService;
import com.hcl.gradedassignment.service.ILikedService;


@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	IBookService service;
	
	@Autowired
	ILikedService likedService;
	
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method= RequestMethod.POST, value = "/addBook") 
	public ResponseEntity<Book> addBook(@RequestBody Book book ) 
	{
			return new ResponseEntity<>(service.addBook(book), HttpStatus.CREATED) ;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method= RequestMethod.DELETE, value = "/deleteBook/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable int id) throws IdNotFoundException
	{
		return new ResponseEntity<>(service.deleteBook(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method= RequestMethod.PUT, value = "/updateBook/{id}")
	public ResponseEntity<Book> updateBookById(@RequestBody Book book,@PathVariable int id) throws IdNotFoundException
	{
		return new ResponseEntity<>(service.updateBook(book, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(method= RequestMethod.GET, value = "/displayBooks")
	public ResponseEntity<List<Book>> displayBooks()
	{
		return new ResponseEntity<>(service.displayBooks(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(method= RequestMethod.GET, value = "/searchBook/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) throws IdNotFoundException
	{
		return new ResponseEntity<>(service.searchBook(id), HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method= RequestMethod.GET, value = "/getLikedBooksByUserId/{id}")
	public ResponseEntity<List<LikedBook>> getLikedBooksByUserId(@PathVariable int id) throws IdNotFoundException
	{
		return new ResponseEntity<>(likedService.getLikedBooksByUserId(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(method= RequestMethod.GET, value = "/addLikedBook/{userId}/{bookId}")
	public ResponseEntity<String> addLikedBookByBookId(@PathVariable int userId, @PathVariable int bookId ) throws IdNotFoundException
	{
		return new ResponseEntity<>(likedService.addBookToUserLikeList(userId, bookId), HttpStatus.OK);
	}

}
