package com.hcl.gradedassignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.gradedassignment.beans.Book;

import com.hcl.gradedassignment.dao.IBookDAO;
import com.hcl.gradedassignment.exception.IdNotFoundException;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	IBookDAO dao;
	
	@Override
	public Book addBook(Book book)  {
		// TODO Auto-generated method stub
		
		List<Book> booklist = new ArrayList<>();
		booklist.add(book);
		return dao.save(book);
	}

	@Override
	public String deleteBook(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Book book = dao.findById(id).orElseThrow(()-> new IdNotFoundException("Book Id Not Exist.!"));
		dao.delete(book);
		return "success";
	}

	@Override
	public Book updateBook(Book book , int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		dao.findById(id).orElseThrow(()-> new IdNotFoundException("Book Id Not Exist.!"));
		return dao.saveAndFlush(book);
	}
	
	@Override
	public List<Book> displayBooks() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	@Override
	public Book searchBook(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return dao.findById(id).orElseThrow(()-> new IdNotFoundException("Book Id Not Exist.!"));
	}


	

}
