
package com.renu.to_let.web.service;

import com.renu.to_let.models.User;

public interface UserService {
  void saveUser(User user,String[] roles);
  User findByUsername(String username);
}
