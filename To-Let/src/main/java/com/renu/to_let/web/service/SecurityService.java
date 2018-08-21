package com.renu.to_let.web.service;

public interface SecurityService {
  String findLoggedInUsername();
  void autologin(String username, String password);
}
