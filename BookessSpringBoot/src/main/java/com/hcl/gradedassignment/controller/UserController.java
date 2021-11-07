package com.hcl.gradedassignment.controller;

import com.hcl.gradedassignment.beans.AuthToken;
import com.hcl.gradedassignment.beans.Book;
import com.hcl.gradedassignment.beans.LoginUser;
import com.hcl.gradedassignment.beans.Role;
import com.hcl.gradedassignment.beans.User;
import com.hcl.gradedassignment.beans.UserDto;
import com.hcl.gradedassignment.exception.IdNotFoundException;
import com.hcl.gradedassignment.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    
    //displayUser
    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id) throws IdNotFoundException{
        return userService.findById(id);
    }

    //addUser
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }
    
    
    //deleteUser
    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method= RequestMethod.DELETE, value = "/deleteUser/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable int id) throws IdNotFoundException
	{
		return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
	}
    
    
    //updateUser
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(method= RequestMethod.PUT, value = "/updateUser/{id}")
	public ResponseEntity<User> updateBookById(@RequestBody UserDto user,@PathVariable int id) throws IdNotFoundException
	{
		return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
	}
    
    
    //assignRole
    @RequestMapping(method= RequestMethod.POST, value = "/assignRole/{id}")
	public ResponseEntity<String> updateBookById(@PathVariable int id, @RequestBody Role role) throws IdNotFoundException
	{
		return new ResponseEntity<>(userService.assignRolesToUser(id, role), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return ResponseEntity.ok("Successfully Logged in");
    }
    
    @RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return ResponseEntity.ok("Successfully Logged out");
    }



}
