package com.hcl.gradedassignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.gradedassignment.beans.Book;

@Repository
public interface IBookDAO extends JpaRepository<Book, Integer> {

}
