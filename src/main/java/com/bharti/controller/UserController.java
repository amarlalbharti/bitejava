package com.bharti.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bharti.domain.Registration;
import com.bharti.service.LoginInfoService;
import com.bharti.service.RegistrationService;

@Controller
public class UserController
{
	@Autowired private RegistrationService registrationService; 
	@Autowired private LoginInfoService loginInfoService; 
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From user profile ..............");
		Registration reg = registrationService.getRegistrationByUserid(principal.getName());
		if(reg != null)
		{
			map.addAttribute("user", reg);
			return "profile";
		}
		return "redirect:login";
	}
	
}
