package com.hcl.gradedassignment.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Entity
@Data
public class LikedBook {

	@Id
	private int likedId;
	private String name;
	private String author;
	
	
	@ManyToMany(mappedBy = "likedBooks", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<User> user;

}
