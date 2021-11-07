package com.hcl.gradedassignment.beans;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long roleId;

    @Column
    private String name;

    @Column
    private String description;
    
    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<User> user;

    
}
