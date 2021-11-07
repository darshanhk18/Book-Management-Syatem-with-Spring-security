package com.hcl.gradedassignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.gradedassignment.beans.Role;

@Repository
public interface IRoleDAO extends JpaRepository<Role, Long>{

}
