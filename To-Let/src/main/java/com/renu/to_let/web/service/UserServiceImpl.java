package com.renu.to_let.web.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.renu.to_let.models.Role;
import com.renu.to_let.models.User;
import com.renu.to_let.web.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
    
  @Override  
  public void saveUser(User user,String[] roles) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    Set<Role> rolesSet = new HashSet<Role>();
    for(String role:roles){
      rolesSet.add(new Role(role));
    }
    user.setRoles(rolesSet);
    userRepository.save(user);
  }
  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
