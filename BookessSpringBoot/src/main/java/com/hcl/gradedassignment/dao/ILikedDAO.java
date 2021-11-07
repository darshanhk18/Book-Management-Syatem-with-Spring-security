package com.hcl.gradedassignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.gradedassignment.beans.LikedBook;

@Repository
public interface ILikedDAO extends JpaRepository<LikedBook, Integer> {

}
