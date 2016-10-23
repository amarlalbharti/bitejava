package com.bharti.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bharti.constraints.Roles;
import com.bharti.constraints.Validation;
import com.bharti.domain.LoginInfo;
import com.bharti.domain.Registration;
import com.bharti.domain.UserRole;
import com.bharti.model.KeynoteModel;
import com.bharti.model.UserRegModel;
import com.bharti.service.LoginInfoService;
import com.bharti.service.MailService;
import com.bharti.service.RegistrationService;

@Controller
public class LoginController 
{
	@Autowired private RegistrationService registrationService; 
	@Autowired private LoginInfoService loginInfoService; 
	@Autowired private MailService mailService; 
	
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
	public String regUser(@ModelAttribute(value = "regForm") @Valid UserRegModel model,BindingResult result,
			@ModelAttribute(value = "reg") Registration reg, BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid,
			ModelMap map, HttpServletRequest request,Principal principal)
	{
		if(model.getUserid() != null)
		{
			if(Validation.validateEmail(model.getUserid()))
			{
				Registration reg1 = registrationService.getRegistrationByUserid(model.getUserid());
				if(reg1 != null)
				{
					result.addError(new FieldError("regForm", "userid", model.getUserid() , false, new String[1],new String[1], "Email is already registered !"));
					return "signup";
				}
			}
			else
			{
				result.addError(new FieldError("regForm", "userid", model.getUserid() , false, new String[1],new String[1], "Please provide valid Email !"));
				return "signup";
			}
		}
		if(result.hasErrors())
		{
			System.out.println("Validtion failed");
			return "signup";
		}
		else
		{
			java.util.Date dt = new java.util.Date();
			java.sql.Date date = new java.sql.Date(dt.getTime());
			
			String uuid = UUID.randomUUID().toString();
			
			login.setForgotpwdid(uuid);
			login.setRegistration(reg);
			reg.setCreateDate(date);
			reg.setLog(login);
			Set<UserRole> roles = new HashSet<UserRole>();
			urole.setUserrole(Roles.ROLE_USER.toString());
			roles.add(urole);
			login.setRoles(roles);
			login.setIsactive("false");
			urole.setLog(login);
			loginInfoService.addLoginInfo(login);
			
			map.addAttribute("regSuccess", "true");
			map.addAttribute("name", reg.getName());
			
			String mailContent="Dear "+reg.getName()+",<br><br><br>"+
 
								"Congratulations, you have successfully registered to BiteJava. <br><br>"+
								 
								"Please find below your user credentials. Please <a href='http://www.bitejava.com/login' >login</a>  and change password for security reasons. For any assistance, please feel free to reach out to us at support@bitejava.com<br><br>"+
								 
								"Username - "+reg.getUserid()+"<br>"+
								"Password - "+model.getPassword()+"<br><br><br>"+
								 
								"Regards,<br>"+
								"Team Bitejava";
			
			
			
			
			mailService.sendMail(reg.getUserid(), "Thank you for registration in BiteJava.com", mailContent);

			
			
			return "redirect:login";
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
	
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotpassword(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From forgot password..............");
		map.addAttribute("resetPwd", "forgot");
		return "forgotpassword";
	}
	
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String forgotpassword(@RequestParam("email") String userid , ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From forgot password..............");
		map.addAttribute("resetPwd", "forgot");
		if(Validation.validateEmail(userid))
		{
			Registration reg = registrationService.getRegistrationByUserid(userid);
			if(reg != null)
			{
				LoginInfo login = loginInfoService.getLoginInfoByUserid(userid);
				if(login != null)
				{
					String uuid = UUID.randomUUID().toString();
					login.setForgotpwdid(uuid);
					loginInfoService.updateLoginInfo(login);
					
					String path_url = (String)request.getSession().getAttribute("path_url");
					String mailContent = "Dear "+reg.getName()+",<br><br><br>"+
										
										"Retrieve your password here <br><br>"+
										"Please click on link below to change your password.<br><br> <a href='"+path_url+"/resetpassword?email="+reg.getUserid()+"&token="+uuid+"' >"+path_url+"/resetpassword?email="+reg.getUserid()+"&token="+uuid+"</a>  <br> <br>For any assistance, please feel free to reach out to us at support@bitejava.com<br><br>"+
										"Username - "+reg.getUserid()+"<br>"+
										"<br><br>"+
										"Regards,<br>"+
										"Team Bitejava";
					
					mailService.sendMail(reg.getUserid(), "Thank you for registration in BiteJava.com", mailContent);
					map.addAttribute("reset", "success");
					return "forgotpassword";
				}
				
			}
			else{
				map.addAttribute("emailError", "This email id is not registered !");
			}
		}
		else
		{
			map.addAttribute("emailError", "Please enter a valid email id !");
		}
		return "forgotpassword";
	}
	
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetpassword(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From resetpassword ..............");
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		
		if(email != null && Validation.validateEmail(email))
		{
			LoginInfo login = loginInfoService.getLoginInfoByUserid(email);
			if(login != null && login.getForgotpwdid() != null && login.getForgotpwdid().equals(token))
			{
				map.addAttribute("token", token);
				map.addAttribute("email", email);
				map.addAttribute("resetPwd", "reset");
				return "forgotpassword";
			}
				
		}
		return "forgotpassword";
	}
	
	
}
