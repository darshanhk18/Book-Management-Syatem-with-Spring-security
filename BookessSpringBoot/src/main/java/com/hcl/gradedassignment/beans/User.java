package com.hcl.gradedassignment.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

import javax.persistence.*;

import java.util.List;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userId;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
 

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    		name = "user_roles", 
    		joinColumns = { @JoinColumn(name = "user_id") }, 
    		inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles;
    
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "user_likes",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn (name = "liked_id")}
	)
	private List<LikedBook> likedBooks;

    
}
