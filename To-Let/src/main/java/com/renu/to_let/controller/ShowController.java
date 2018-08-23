package com.renu.to_let.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.to_let.addservice_repository.AddServiceRepository;

@Controller
public class ShowController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShowController.class);
   @Autowired
	private AddServiceRepository addServiceRepository;
	@RequestMapping(value = "/view-services")
	public String viewServices(Model model) {
		LOGGER.info("From class:ShowController,,method : viewServices");
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "View-Services");
		model.addAttribute("jsonurl", "/viewServices");

		return "view-services";
	}

	@RequestMapping(value = "/view-by-categories")
	public String viewByCategory(@RequestParam("rentTypes") String categories, Model model) {
		LOGGER.info("From class:ShowController,,method : viewByCategory");
		LOGGER.info("get by @RequestParam : " + categories);
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		model.addAttribute("jsonurl", "/json-category?category=" + categories);

		return "view-by-category";
	}
	
	
	

	@RequestMapping(value = "/view-by-countries")

	public String viewByCountry(@RequestParam("country") String country, Model model) {
		LOGGER.info("From class:ShowController,,method : viewByCountry");
		LOGGER.info("get by @RequestParam : " + country);
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		model.addAttribute("databycountry",addServiceRepository.findByCountry(country));
		
		return "view-by-country";
	}

	@RequestMapping(value = "/view-by-categories-countries")
	public String viewByCountryCategory(@RequestParam("rentTypes") String categories,@RequestParam("countries") String countries, Model model) {
		LOGGER.info("From class:ShowController,,method : viewByCountryCategory");
		LOGGER.info("get by @RequestParam : " + categories+" ,"+countries);
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		model.addAttribute("jsonurl", "/json-country-category?category=" + categories+"&country="+countries);
		
		return "view-by-category";
	}
}
