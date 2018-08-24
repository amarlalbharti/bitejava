package com.bharti.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bharti.constraints.Validation;
import com.bharti.domain.ContactUs;
import com.bharti.domain.Keynote;
import com.bharti.domain.NewsLater;
import com.bharti.domain.Subject;
import com.bharti.model.ContactUsModel;
import com.bharti.model.KeynoteModel;
import com.bharti.service.ContactUsService;
import com.bharti.service.KeynoteService;
import com.bharti.service.NewsLaterService;
import com.bharti.service.SubjectService;

@Controller
public class IndexController
{
	@Autowired private NewsLaterService newsLaterService; 
	@Autowired private KeynoteService keynoteService; 
	@Autowired private SubjectService subjectService;
	@Autowired private ContactUsService contactUsService;
	
	private Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Hello from index");

		Map<Long, List<Keynote>> mapList = new HashMap<>();
		List<Subject> sList = subjectService.getSubjectsListForHomePage();
		for(Subject sub : sList)
		{
			mapList.put(sub.getSid(), keynoteService.getKeynotesForHomePage(sub.getSid()));
		}
		map.addAttribute("mapList", mapList);
		map.addAttribute("sList", sList);
		return "index";
	}
	
	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public String test(@PathVariable("id") String productId , ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		System.out.println("Hello from test");
		return "test";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		System.out.println("::::::::::::::: from error");
		System.out.println("URL : "+ request.getRequestURL());
		
		
		return "error";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		System.out.println("::::::::::::::: from test");
		System.out.println("URL : "+ request.getRequestURL());
		
		
		return "test";
	}
	
	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutus(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("From page about us");
		return "aboutus";
	}
	
	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactus(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("From page contact us");
		map.addAttribute("form_contactus", new ContactUsModel());
		return "contactus";
	}
	@RequestMapping(value = "/submitContactUs", method = RequestMethod.POST)
	public String submitContactUs(@ModelAttribute(value = "form_contactus") @Valid ContactUsModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if (result.hasErrors())
		{
				return "contactus";
		}else {
			ContactUs contactUs = new ContactUs();
			contactUs.setName(model.getName());
			contactUs.setEmail(model.getEmail());
			contactUs.setMobile(model.getMobile());
			contactUs.setComment(model.getComment());
			contactUs.setMarkAsRead(Boolean.FALSE);
			contactUs.setCreateDate(new Date());
			if(principal != null) {
				contactUs.setCreateBy(principal.getName());
			}else {
				contactUs.setCreateBy("GUEST");
			}
			this.contactUsService.add(contactUs);
			request.getSession().setAttribute("successMsg", "Thank you for your feedback/request. We will respond soon.");
			return "redirect:contactus";
		}
	}
	@RequestMapping(value = "/privacy_policy", method = RequestMethod.GET)
	public String privacyPolicy(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("From page contact us");
		return "privacyPolicy";
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public String subscribe(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String email = request.getParameter("email");
		if(email != null && email.trim().length() > 0 && Validation.validateEmail(email) )
		{
			if(newsLaterService.getNewsLater(email) == null)
			{
				Date date = new Date();
				java.sql.Date dt = new java.sql.Date(date.getTime());
				
				NewsLater subs = new NewsLater();
				subs.setEmail(email);
				subs.setCreateDate(dt);
				newsLaterService.addNewsLater(subs);
				map.addAttribute("subscribed", "success");
			}
			else
			{
				map.addAttribute("subscribed", "exist");
			}
			map.addAttribute("email", email);
			return "subscribe";
		}
		System.out.println("From page about us");
		return "subscribe";
	}
	
	@RequestMapping(value = "/theme/ckeditor", method = RequestMethod.POST)
	public @ResponseBody String ckeditor(ModelMap map, HttpServletRequest request, Principal principal)
	{
		return "/index";
	}
}
