package com.example.modestack_assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.modestack_assignment.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUserName(String userName);

}
