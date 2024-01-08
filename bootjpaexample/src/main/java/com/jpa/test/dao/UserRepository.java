package com.jpa.test.dao;

import org.springframework.data.repository.CrudRepository;

import com.jpa.test.entities.User;


//Perform crud op on <object,id>

public interface UserRepository extends CrudRepository<User, Integer> {

}
