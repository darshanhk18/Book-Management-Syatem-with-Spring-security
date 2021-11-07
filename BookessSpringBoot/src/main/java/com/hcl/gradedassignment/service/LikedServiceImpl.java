package com.hcl.gradedassignment.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.gradedassignment.beans.Book;
import com.hcl.gradedassignment.beans.LikedBook;
import com.hcl.gradedassignment.beans.User;
import com.hcl.gradedassignment.dao.IBookDAO;
import com.hcl.gradedassignment.dao.ILikedDAO;
import com.hcl.gradedassignment.dao.IUserDAO;
import com.hcl.gradedassignment.exception.IdNotFoundException;

@Service
public class LikedServiceImpl implements ILikedService {
	
	@Autowired
	IBookDAO bookDao;
	
	@Autowired
	ILikedDAO likedDao;
	
	@Autowired
	private IUserDAO userDao;

	@Override
	public String addBookToUserLikeList(long userId , int bookId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Book book = bookDao.findById(bookId).orElseThrow(()-> new IdNotFoundException("Book Id Not Exist.!"));
		User user = userDao.findById(userId).orElseThrow(()-> new IdNotFoundException("User Id Not Exist.!"));
		LikedBook likedBook = new LikedBook();
		List<LikedBook> likelist = new ArrayList<>();
		likedBook.setLikedId(book.getId());
		likedBook.setName(book.getName());
		likedBook.setAuthor(book.getAuthor());
		likelist.add(likedBook);
		
		user.setLikedBooks(likelist);
		
		userDao.saveAndFlush(user);
		
		return "Added to Liked List";
	}
	
	@Override
	public List<LikedBook> getLikedBooksByUserId(long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findById(id).orElseThrow(()-> new IdNotFoundException("Employee Id Not Exist.!"));
		return user.getLikedBooks();
	}
	
	
}
