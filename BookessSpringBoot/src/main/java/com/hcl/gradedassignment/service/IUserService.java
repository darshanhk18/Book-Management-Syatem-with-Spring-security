package com.hcl.gradedassignment.service;

import java.util.List;

import com.hcl.gradedassignment.beans.Role;
import com.hcl.gradedassignment.beans.User;
import com.hcl.gradedassignment.beans.UserDto;
import com.hcl.gradedassignment.exception.IdNotFoundException;

public interface IUserService {

    public User save(UserDto user);
    
    public List<User> findAll();
    
    public String delete(long id) throws IdNotFoundException;
    
    public User findOne(String username);
    
    public User findById(Long id)throws IdNotFoundException;
    
    public User updateUser(UserDto user, long id) throws IdNotFoundException;
    
    public String assignRolesToUser(long id, Role role)throws IdNotFoundException;
    
    
}
