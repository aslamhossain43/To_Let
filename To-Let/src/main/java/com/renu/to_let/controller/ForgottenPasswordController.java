package com.renu.to_let.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.to_let.addservice_repository.User_Repository;
import com.renu.to_let.models.User;


@Controller
public class ForgottenPasswordController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ForgottenPasswordController.class);
	@Autowired
	private User_Repository	userRepository;		
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@RequestMapping(value="/set-forgotten-password")
			public String showForgottenPassword(Model model) {
				LOGGER.info("From class : ForgottenPasswordController, method : showForgottenPassword()");
					return "forgotten-password";
				
			}
			
			
			
			@RequestMapping(value="/reset-password",method=RequestMethod.POST)
			public String resetPassword(@RequestParam("username") String username,@RequestParam("password") String password,
					@RequestParam("confirmPassword") String confirmPassword, Model model,String[]roles) {
				LOGGER.info("From class : ForgottenPasswordController, method : resetPassword()");
				LOGGER.info("Getting email : "+username);
				LOGGER.info("Getting password : "+password);
				
				LOGGER.info("Getting confirm password : "+confirmPassword);
				User user=userRepository.getByUsername(username);
				
				if (user==null) {
					model.addAttribute("message", "Invalid email ! please complete registration ");
					return "forgotten-password";
				}
			          if (password.equals(confirmPassword)) {
			        	  String pwd=bCryptPasswordEncoder.encode(password);
						userRepository.updatePassword(pwd,username);
					model.addAttribute("message", "Your password has been completed reset successfully !!!");
					
					
					return "sec-login";
			          }else {
						model.addAttribute("message", "Invalid email or password");
						return "forgotten-password";
					}
					
				
			}
	
	
	

}
