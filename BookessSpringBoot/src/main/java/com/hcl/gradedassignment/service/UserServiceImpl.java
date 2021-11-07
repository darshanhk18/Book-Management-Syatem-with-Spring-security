package com.hcl.gradedassignment.service;

import com.hcl.gradedassignment.beans.Book;
import com.hcl.gradedassignment.beans.Role;
import com.hcl.gradedassignment.beans.User;
import com.hcl.gradedassignment.beans.UserDto;
import com.hcl.gradedassignment.dao.IUserDAO;
import com.hcl.gradedassignment.exception.IdNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, IUserService {
	
	@Autowired
	private IUserDAO userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public String delete(long id) throws IdNotFoundException {
		User user = userDao.findById(id).orElseThrow(()-> new IdNotFoundException("User Id Not Exist.!"));
		userDao.delete(user);
		return "success";
	}
	
	@Override
	public User updateUser(UserDto user , long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		User u = userDao.findById(id).orElseThrow(()-> new IdNotFoundException("User Id Not Exist.!"));
		u.setUsername(user.getUsername());
		u.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.saveAndFlush(u);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) throws IdNotFoundException {
		return userDao.findById(id).orElseThrow(()-> new IdNotFoundException("User Id Not Exist.!"));
	}

	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }

	@Override
	public String assignRolesToUser(long id, Role role) throws IdNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findById(id).orElseThrow(()-> new IdNotFoundException("User Id Not Exist.!"));
		Role newRole = new Role();
		newRole.setRoleId(role.getRoleId());
		newRole.setName(role.getName());
		newRole.setDescription(role.getDescription());
		
		List<Role> roleList = new ArrayList<>();
		roleList.add(newRole);
		user.setRoles(roleList);
		
		userDao.saveAndFlush(user);
		
		return "Role Assigned Successfully";
	}

	
}
