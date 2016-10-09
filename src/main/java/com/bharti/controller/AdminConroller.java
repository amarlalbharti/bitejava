package com.bharti.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bharti.service.KeynoteDetailService;
import com.bharti.service.KeynoteService;
import com.bharti.service.SubjectService;

@Controller
public class AdminConroller
{
	@Autowired private KeynoteService keynoteService; 
	@Autowired private SubjectService subjectService; 
	@Autowired private KeynoteDetailService keynoteDetailService; 
	
	private Logger logger = Logger.getLogger(AdminConroller.class);
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Redirecting from admin to adminDashboard");
		return "redirect:adminDashboard";
	}
	
	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public String dashboard(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("rsList", subjectService.getRecentSubjects(0, 10));
		map.addAttribute("rkList", keynoteService.getRecentKeynotes(0, 10));
		logger.info("From admin dashboard");
		return "dashboard";
	}
	
}
