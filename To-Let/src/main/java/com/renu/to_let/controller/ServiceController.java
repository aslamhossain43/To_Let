package com.renu.to_let.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.to_let.addservice_repository.AddServiceRepository;
import com.renu.to_let.models.AddService;
import com.renu.to_let.utility.FileUploadUtility;

@Controller
public class ServiceController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ServiceController.class);
	@Autowired
	private AddServiceRepository addServiceRepository;
	

	@RequestMapping(value="/showAddservice")
	public String showAddService(Model model) {
		LOGGER.info("From showService method");
		model.addAttribute("services", new AddService());
		model.addAttribute("title","AddServices");
		return "add-services";
	}
	
	
	@RequestMapping(value="/updateAddservice",method=RequestMethod.GET)
	public String showupdateAddService(@RequestParam("id") long id, Model model) {
		LOGGER.info("From showupdateAddService method");
		AddService addService=addServiceRepository.getOne(id);
		model.addAttribute("title","AddServices");
		model.addAttribute("services", addService);
		return "add-services";
	}
	
	@RequestMapping(value="/addservices",method=RequestMethod.POST)
	public String addService(@ModelAttribute("services") AddService services,BindingResult bindingResult,
			Model model,HttpServletRequest vRequest,HttpServletRequest iRequest) {
		LOGGER.info("From addService method");
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "Your operation has not been completed successfully !!!");
			return "add-services";
		}

		if (!services.getvFile().getOriginalFilename().equals("")) {
			FileUploadUtility.videoUploadFile(vRequest, services.getvFile(), services.getvCode());
		}
		if (!services.getiFile().getOriginalFilename().equals("")) {
			FileUploadUtility.imageUploadFile(iRequest, services.getiFile(),services.getiCode());
		}
		
		addServiceRepository.save(services);
		
		model.addAttribute("message", "Your operation has been completed successfully !!!");
		
		return "add-services";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
