package com.renu.to_let.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.renu.to_let.addservice_repository.AddServiceRepository;
import com.renu.to_let.models.AddService;

@ControllerAdvice
public class AddressController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	AddServiceRepository addServiceRepository;

	@ModelAttribute("servicestable")
	public List<AddService> getAllServices() {
		LOGGER.info("From class : AddressController,method :getAllServices()");
		return addServiceRepository.getServicesTable();

	}

}
