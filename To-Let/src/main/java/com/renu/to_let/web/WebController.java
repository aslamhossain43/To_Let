package com.renu.to_let.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renu.to_let.models.User;
import com.renu.to_let.web.service.SecurityService;
import com.renu.to_let.web.service.UserService;


@Controller
public class WebController {
	@Autowired
	UserService userService;

	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/main")
	public String main() {
		return "sec-main";
	}

	@RequestMapping("/login")
	public String login(Model model, String error, String logout, HttpServletRequest request) {
		if (logout != null) {
			model.addAttribute("logout", "You have been logged out successfully.");
		}
		
		model.addAttribute("title", "Login");
		
		return "sec-login";
	}

	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(Model model, String username) {
		model.addAttribute("message", "Login has been completed successfully.");
	
		return "home";
	}
	
	
	
	@RequestMapping(value = "/loginError")
	public String loginError(Model model, String username) {
		model.addAttribute("error", "Your username and password is invalid.");
		model.addAttribute("title", "Login");
	
		return "sec-login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("title", "Registration");
		return "sec-registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			String[] roles) {
			
		String password = userForm.getPassword();
		if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
			model.addAttribute("message","Your password and confirm password is not equal !!! ");
			return "sec-registration";
		}
		userService.saveUser(userForm, roles);
		securityService.autologin(userForm.getUsername(), password);
		model.addAttribute("message", "Your Registration has been completed successfully !!!");
		return "sec-registration";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "sec-admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "sec-user";
	}

	@RequestMapping("/403")
	public String access() {
		return "sec-access";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,Model model) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if (authentication!=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		
		model.addAttribute("message","Logout has been completed successfully");
		return "redirect:/login?logout";
	}
	
	
}
