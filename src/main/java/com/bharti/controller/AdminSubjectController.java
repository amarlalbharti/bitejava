package com.bharti.controller;

import java.io.File;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bharti.constraints.ProjectConfig;
import com.bharti.constraints.Roles;
import com.bharti.constraints.StaticValues;
import com.bharti.domain.Subject;
import com.bharti.model.KeynoteModel;
import com.bharti.model.SubjectModel;
import com.bharti.service.SubjectService;

@Controller
public class AdminSubjectController 
{
	
	@Autowired private SubjectService subjectService;
	
	private Logger logger = Logger.getLogger(AdminSubjectController.class);
	
	@RequestMapping(value = "/adminSubjects", method = RequestMethod.GET)
	public String adminSubjects(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("Hello from admin dashboard : ");
//		if(request.isUserInRole(Roles.ROLE_ADMIN)) {
//			map.addAttribute("sList", subjectService.getAllSubjectsList(0, 10));
//		} else if(request.isUserInRole(Roles.ROLE_PUBLISHER)) {
//			map.addAttribute("sList", subjectService.getAllSubjectsList(0, 10, principal.getName()));
//		}
		return "subjects";
	}
	
	@RequestMapping(value = "/getSubjectList", method = RequestMethod.GET)
	public String subjectList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String pn = request.getParameter("pn");
		int pageno = 1;
		if(pn != null && pn.trim().length() > 0) {
			try {
				pageno = Integer.parseInt(pn);
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		logger.info("Get the subject list for pn :"+ pn);
		
		if(request.isUserInRole(Roles.ROLE_ADMIN)) {
			map.addAttribute("sList", subjectService.getAllSubjectsList((pageno-1)*StaticValues.rpp, StaticValues.rpp));
			map.addAttribute("total_count", (int)subjectService.countSubjects());
		} else if(request.isUserInRole(Roles.ROLE_PUBLISHER)) {
			map.addAttribute("sList", subjectService.getAllSubjectsList((pageno-1)*StaticValues.rpp, StaticValues.rpp, principal.getName()));
			map.addAttribute("total_count", (int)subjectService.countSubjects(principal.getName()));
		}
		map.addAttribute("rpp", StaticValues.rpp);
		map.addAttribute("pn", pageno);
		return "subjectList";
	}
	
	
	
	@RequestMapping(value = "/adminAddSubject", method = RequestMethod.GET)
	public String addSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("Hello from admin dashboard");
		map.addAttribute("form_subject", new SubjectModel());
		return "addSubject";
	}
	
	
	@RequestMapping(value = "/adminAddSubject", method = RequestMethod.POST)
	public String add_Subject(@ModelAttribute(value = "form_subject") @Valid SubjectModel model,BindingResult result, ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("Show on home page " + model.getSubject_image());
		if(model.getUrl() == null || model.getUrl().trim().length() < 3)
		{
			result.addError(new FieldError("form_subject", "url", model.getUrl() , false, new String[1],new String[1], "Please enter valid url !"));
			return "addSubject";
		}
		else
		{
			String url = model.getUrl().replaceAll("[^a-zA-Z0-9]+","_");
			 if(subjectService.getSubjectById(url.toLowerCase()) != null)
			 {
				 result.addError(new FieldError("form_subject", "url", model.getUrl() , false, new String[1],new String[1], "Url already exist, Please enter different url !"));
				 return "addSubject";
			 }
		}
		if (result.hasErrors())
		{
			System.out.println("in validation");
			return "addSubject";
		}
		else
		{
			String  btnType = request.getParameter("submit");
			System.out.println("Submited Buttom value : "+ btnType);
			
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			
			
			Subject sub = new Subject();
			sub.setSubject(model.getSubject());
			sub.setCreateDate(dt);
			sub.setShowOnHomePage(model.isShowOnHomePage());
			sub.setUrl(model.getUrl().replaceAll("[^a-zA-Z0-9]+","_").toLowerCase());
			if(btnType != null && btnType.equals("Save And Publish"))
			{
				sub.setPublishDate(dt);
			}
			try
			{
				sub.setDisplayOrder(Integer.parseInt(model.getDisplayOrder()));
				
			}
			catch(NumberFormatException e)
			{
				sub.setDisplayOrder(-1);
				sub.setPublishDate(null);
				e.printStackTrace();
			}
			long sid = subjectService.addSubject(sub);
			sub.setSid(sid);
			try
			{
				MultipartFile sub_img = model.getSubject_image();
				if(sub_img != null)
				{
					String img_name = sub_img.getOriginalFilename();
					if(img_name != null && img_name.trim().length() > 0)
					{
						img_name = img_name.replaceAll("[^a-zA-Z0-9.-]", "_");
						sub.setSubject_image(img_name);
						File img = new File (ProjectConfig.upload_path+"/subjects/"+sid+"/subject_image/"+img_name);
						
						if(!img.exists())
						{
							img.mkdirs();
						}
						sub_img.transferTo(img);  
						subjectService.updateSubject(sub);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return  "redirect:adminSubjects";
		}
	}
	
	@RequestMapping(value = "/adminEditSubject", method = RequestMethod.GET)
	public String editSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("Hello from Edit Subject");
		String sid = request.getParameter("sid");
		if(sid != null && sid.trim().length() > 0)
		{
			try 
			{
				Subject subject = subjectService.getSubjectById(Long.parseLong(sid));
				if(subject != null )
				{
					if(request.isUserInRole(Roles.ROLE_PUBLISHER) && 
							(subject.getLoginInfo() == null || 
							!subject.getLoginInfo().getUserid().equals(principal.getName()))
							) {
						logger.error(principal.getName() + " is not allowed to update others subject for sid :"+subject.getSid() + " and subject: "+subject.getSubject());
						return  "redirect:error";
					}
					SubjectModel model = new SubjectModel();
					model.setSid(subject.getSid());
					model.setSubject(subject.getSubject());
					model.setDisplayOrder(String.valueOf(subject.getDisplayOrder()));
					model.setUrl(subject.getUrl());
					model.setShowOnHomePage(subject.isShowOnHomePage());
					map.addAttribute("form_subject", model);
					return "editSubject";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return  "redirect:adminSubjects";
	}
	@RequestMapping(value = "/adminEditSubject", method = RequestMethod.POST)
	public String adminEditSubject(@ModelAttribute(value = "form_subject") @Valid SubjectModel model,BindingResult result, ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		if(model.getUrl() == null || model.getUrl().trim().length() < 3)
		{
			result.addError(new FieldError("form_subject", "url", model.getUrl() , false, new String[1],new String[1], "Please enter valid url !"));
			return "editSubject";
		}
		
		if (result.hasErrors())
		{
			System.out.println("in validation");
			return "editSubject";
		}
		else
		{
			Subject sub = subjectService.getSubjectById(model.getSid());
			if(sub != null)
			{
				if(request.isUserInRole(Roles.ROLE_PUBLISHER) && 
						(sub.getLoginInfo() == null || 
						!sub.getLoginInfo().getUserid().equals(principal.getName()))
						) {
					logger.error(principal.getName() + " is not allowed to update others subject for sid :"+sub.getSid() + " and subject: "+sub.getSubject());
					return  "redirect:error";
				}
				String  btnType = request.getParameter("submit");
				System.out.println("Submited Buttom value : "+ btnType);
				
				Date date = new Date();
				java.sql.Date dt = new java.sql.Date(date.getTime());
				
				
				sub.setSubject(model.getSubject());
				sub.setCreateDate(dt);
				sub.setShowOnHomePage(model.isShowOnHomePage());
				sub.setUrl(model.getUrl().replaceAll("[^a-zA-Z0-9]+","_").toLowerCase());
				if(sub.getPublishDate() == null && btnType != null && btnType.equals("Save And Publish"))
				{
					sub.setPublishDate(dt);
				}
				try
				{
					sub.setDisplayOrder(Integer.parseInt(model.getDisplayOrder()));
				}
				catch(NumberFormatException e)
				{
					sub.setDisplayOrder(-1);
					sub.setPublishDate(null);
					e.printStackTrace();
				}
				try
				{
					MultipartFile sub_img = model.getSubject_image();
					if(sub_img != null)
					{
						String img_name = sub_img.getOriginalFilename();
						if(img_name != null && img_name.trim().length() > 0)
						{
							img_name = img_name.replaceAll("[^a-zA-Z0-9.-]", "_");
							sub.setSubject_image(img_name);
							File img = new File (ProjectConfig.upload_path+"/subjects/"+sub.getSid()+"/subject_image/"+img_name);
							
							if(!img.exists())
							{
								img.mkdirs();
							}
							sub_img.transferTo(img);  
							subjectService.updateSubject(sub);
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				subjectService.updateSubject(sub);
				
			}
			return  "redirect:adminSubjects";
		}
	}
	
	
	
	
	@RequestMapping(value = "/unpublishSubject", method = RequestMethod.GET)
	@ResponseBody
	public String unpublishSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String sid = request.getParameter("sid");
		if(sid != null && sid.trim().length() > 0)
		{
			try 
			{
				Subject subject = subjectService.getSubjectById(Long.parseLong(sid));
				if(request.isUserInRole(Roles.ROLE_PUBLISHER) && 
						(subject.getLoginInfo() == null || 
						!subject.getLoginInfo().getUserid().equals(principal.getName()))
						) {
					logger.error(principal.getName() + " is not allowed to unpublish others subject for sid :"+subject.getSid() + " and subject: "+subject.getSubject());
				} else if(subject != null ) {
					subject.setPublishDate(null);
					subjectService.updateSubject(subject);
					obj.put("success", true);
					return obj.toJSONString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		obj.put("success", false);
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/publishSubject", method = RequestMethod.GET)
	@ResponseBody
	public String publishSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String sid = request.getParameter("sid");
		if(sid != null && sid.trim().length() > 0)
		{
			try 
			{
				Subject subject = subjectService.getSubjectById(Long.parseLong(sid));
				if(request.isUserInRole(Roles.ROLE_PUBLISHER) && 
						(subject.getLoginInfo() == null || 
						!subject.getLoginInfo().getUserid().equals(principal.getName()))
						) {
					logger.error(principal.getName() + " is not allowed to publish others subject for sid :"+subject.getSid() + " and subject: "+subject.getSubject());
				} else if(subject != null ) {
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					
					subject.setPublishDate(dt);
					subjectService.updateSubject(subject);
					obj.put("success", true);
					return obj.toJSONString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		obj.put("success", false);
		return obj.toJSONString();
	}
	@RequestMapping(value = "/adminDeleteSubject", method = RequestMethod.GET)
	@ResponseBody
	public String adminDeleteSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String sid = request.getParameter("sid");
		if(sid != null && sid.trim().length() > 0)
		{
			try 
			{
				Subject subject = subjectService.getSubjectById(Long.parseLong(sid));
				if(request.isUserInRole(Roles.ROLE_PUBLISHER) && 
						(subject.getLoginInfo() == null || 
						!subject.getLoginInfo().getUserid().equals(principal.getName()))
						) {
					logger.error(principal.getName() + " is not allowed to delete others subject for sid :"+subject.getSid() + " and subject: "+subject.getSubject());
				} else if(subject != null ) {
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					
					subject.setDeleteDate(dt);
					subjectService.updateSubject(subject);
					obj.put("success", true);
					return obj.toJSONString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		obj.put("success", false);
		return obj.toJSONString();
	}
	
	
	
}
