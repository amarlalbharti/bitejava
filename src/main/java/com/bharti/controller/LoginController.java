package com.bharti.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bharti.constraints.Roles;
import com.bharti.domain.Registration;
import com.bharti.model.KeynoteModel;
import com.bharti.model.UserRegModel;
import com.bharti.service.RegistrationService;

@Controller
public class LoginController 
{
	@Autowired private RegistrationService registrationService; 
	private Logger logger = Logger.getLogger(LoginController.class);
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(principal != null)
		{
//			return "redirect:userHome";
		}
		System.out.println("from index page of index controller");
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("regForm", new UserRegModel());
		return "signup";
	}
	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	public String regUser(@ModelAttribute(value = "regForm") @Valid UserRegModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if(result.hasErrors())
		{
			System.out.println("Validtion failed");
			return "signup";
		}
		else
		{
			return "signup";
		}
	}
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return It redirect to dashboard page based on user loged in role. Like for admin, it redirect to admin dashboard.
	 */
	@RequestMapping(value = "/getLogedIn", method = RequestMethod.GET)
	public String getLogedIn(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Inside getLogedIn  principal : " + principal);
		Registration reg = registrationService.getRegistrationByUserid(principal.getName());
		if(reg != null)
		{
			logger.info("Login user name  : " + reg.getName());
			request.getSession(true).setAttribute("registration", reg);
			return "redirect:userHome";
		}
		return "redirect:login";

	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return redirect to the index page after  login failed
	 */
	@RequestMapping(value = "/failtologin", method = RequestMethod.GET)
	public String failtologin(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("from failtologin page of index controller");
		String error="true";
		return "redirect:/login?error="+error;
		
	}
	
	/**
	 * Destroy user session and delete cookies for this JSESSIONID
	 * @param map
	 * @param request
	 * @param principal
	 * @return redirect to the index page after  login failed
	 */
	
	@RequestMapping(value = "/insertLogOut", method = RequestMethod.GET)
	public @ResponseBody String insertLogOut(ModelMap map, HttpServletRequest request)
	{
		System.out.println("from logout page");
		return "logedOut";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("from logout successfully");
		
		return "redirect:/login";
		
	}
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return It redirect to dashboard page based on user loged in role. Like for admin, it redirect to admin dashboard.
	 */
	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userHome(ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(request.isUserInRole(Roles.ROLE_ADMIN))
		{
			return "redirect:adminDashboard";
		}
		
		else
		{
			return "redirect:index";
		}
	}
}
