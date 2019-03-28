package com.yrd.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yrd.farm.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
