package com.jasperproject.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jasperproject.Models.*;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
