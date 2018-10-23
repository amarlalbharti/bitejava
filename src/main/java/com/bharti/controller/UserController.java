package com.bharti.controller;

import java.io.File;
import java.security.Principal;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;

import com.bharti.constraints.DateFormats;
import com.bharti.constraints.ProjectConfig;
import com.bharti.constraints.RegistrationUtils;
import com.bharti.constraints.SeoConstants;
import com.bharti.constraints.StaticMethods;
import com.bharti.constraints.Validation;
import com.bharti.domain.Registration;
import com.bharti.domain.UploadFile;
import com.bharti.model.ChangePasswordModel;
import com.bharti.model.KeynoteModel;
import com.bharti.model.ProfileModel;
import com.bharti.service.CommentService;
import com.bharti.service.LoginInfoService;
import com.bharti.service.RegistrationService;

@Controller
public class UserController
{
	@Autowired private RegistrationService registrationService; 
	@Autowired private LoginInfoService loginInfoService; 
	@Autowired private CommentService commentService; 
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From user profile ..............");
		if(principal != null) {
			Registration reg = registrationService.getRegistrationByUserid(principal.getName());
			if(reg != null)
			{
				map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
				map.addAttribute("pageDescription", RegistrationUtils.getUserFullDetail(reg));
				map.addAttribute("pageTitle", RegistrationUtils.getFullName(reg)+SeoConstants.SEO_POST_TITLE);
				
				ProfileModel model = new ProfileModel();
				model.setEmail(reg.getLoginInfo().getUserid());
				model.setName(reg.getName());
				model.setGender(reg.getGender());
				model.setDob(reg.getDob()!= null?DateFormats.ddMMyyyy.format(reg.getDob()):"");
				model.setContact(reg.getContactno());
				map.addAttribute("user", reg);
				map.addAttribute("profile_form", model);
				return "profile";
			}
		}
		
		return "redirect:login";
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute(value = "profile_form") @Valid ProfileModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if(principal != null) {
			try {
				if (result.hasErrors() || (model.getDob()!= null &&model.getDob().trim().length() > 0 && !Validation.isValidDate(model.getDob()))) {
					if(model.getDob()!= null &&model.getDob().trim().length() > 0 && !Validation.isValidDate(model.getDob())) {
						result.addError(new FieldError("profile_form", "dob", model.getDob() , false, new String[1],new String[1], "DOB must be in format dd/mm/yyyy !"));
					}
					Registration reg = registrationService.getRegistrationByUserid(principal.getName());
					map.addAttribute("user", reg);
					logger.error("error in validation : " + result.getErrorCount());
					request.getSession().setAttribute("success", true);
					request.getSession().setAttribute("msg", "Profile updated successfully !");
					
					return "profile";
				}else {
					Registration reg = registrationService.getRegistrationByUserid(principal.getName());
					if(reg != null) {
						reg.setName(model.getName());
						reg.setGender(model.getGender());
						reg.setDob(DateFormats.ddMMyyyy.parse(model.getDob()));
						reg.setModifyDate(new Date());
						reg.setContactno(model.getContact());
						String path = null;
						MultipartFile uploadfile = model.getFile();
						if(uploadfile != null) {
							String file_name = uploadfile.getOriginalFilename();
							if(file_name != null && file_name.trim().length() > 0) {
								file_name = file_name.replaceAll("[^a-zA-Z0-9.-]", "_");
								path = "/uploadedfiles/profile/"+reg.getLoginInfo().getUserid()+"/"+file_name;
								File file = new File (ProjectConfig.upload_path+path);
								if(!file.exists()) {
									logger.info("File dir not Exist : "+path);
									file.mkdirs();
								}
								uploadfile.transferTo(file);  
								
							}
						}
						if(path != null) {
							reg.setProfileImage(path);
						}
						registrationService.updateRegistration(reg);
						request.getSession().setAttribute("success", true);
						request.getSession().setAttribute("msg", "Profile updated successfully !");
						return "redirect:profile";
					}
				}
				
			}catch (Exception e) {
				logger.error("Error : ", e);
			}
			
		}
		return "redirect:login";
	}
	
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String editPassword(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From user profile ..............");
		Registration reg = registrationService.getRegistrationByUserid(principal.getName());
		if(reg != null) {
			map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
			map.addAttribute("pageDescription", RegistrationUtils.getUserFullDetail(reg));
			map.addAttribute("pageTitle", "Change Password :"+RegistrationUtils.getFullName(reg)+SeoConstants.SEO_POST_TITLE);
			map.addAttribute("user", reg);
			map.addAttribute("form_pwd", new ChangePasswordModel());
			
			return "changePassword";
		}
		return "redirect:login";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute(value = "form_pwd") @Valid ChangePasswordModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if(principal != null) {
			if (result.hasErrors() || !model.getNewPwd().equals(model.getConfPwd())) {
				if(!model.getNewPwd().equals(model.getConfPwd())) {
					result.addError(new FieldError("form_pwd", "confPwd", model.getConfPwd() , false, new String[1],new String[1], "Password does not matched !"));
				}
				map.addAttribute("user", registrationService.getRegistrationByUserid(principal.getName()));
				return "changePassword";
			}else {
				
				String resp = loginInfoService.changeUserPassword(principal.getName(), model.getOldPwd(), model.getNewPwd());
				if(resp.equals("success")) {
					request.getSession().setAttribute("success", true);
					request.getSession().setAttribute("msg", "Password change successfully");
					return "redirect:changePassword";
				}else if(resp.equals("notmatch")) {
					request.getSession().setAttribute("msg", "Old password is not correct !");
				}else if(resp.equals("notexist")) {
					request.getSession().setAttribute("msg", "User does not exists !");
				}else {
					request.getSession().setAttribute("msg", "Some error occured !");
				}
				request.getSession().setAttribute("hasError", true);
				return "redirect:changePassword";
				
			}
		}
		return "redirect:login";
	}
	
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public String comments(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From user profile ..............");
		if(principal != null) {
			Registration reg = registrationService.getRegistrationByUserid(principal.getName());
			if(reg != null) {
				map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
				map.addAttribute("pageDescription", "Comments added by user "+reg.getName());
				map.addAttribute("pageTitle", "My Comments :"+RegistrationUtils.getFullName(reg)+SeoConstants.SEO_POST_TITLE);
				map.addAttribute("comments", commentService.getCommentByUserid(principal.getName(), 0, 10000));
				map.addAttribute("user", reg);
				return "comments";
			}
		}
		return "redirect:login";
	}
	
	
}
