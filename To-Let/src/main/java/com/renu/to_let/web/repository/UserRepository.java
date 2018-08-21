package com.renu.to_let.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renu.to_let.models.User;


public interface UserRepository extends JpaRepository<User, Long>{
  public User findByUsername(String username);
}
