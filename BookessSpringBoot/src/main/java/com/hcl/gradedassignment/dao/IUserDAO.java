package com.hcl.gradedassignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.hcl.gradedassignment.beans.User;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
