package com.hcl.gradedassignment.service;

import java.util.List;


import com.hcl.gradedassignment.beans.LikedBook;
import com.hcl.gradedassignment.beans.Role;
import com.hcl.gradedassignment.exception.IdNotFoundException;


public interface ILikedService {

	public String addBookToUserLikeList(long userId, int bookId) throws IdNotFoundException ;
	
	public List<LikedBook> getLikedBooksByUserId(long id) throws IdNotFoundException ;
	
	
	
}
