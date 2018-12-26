package com.bharti.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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

import com.bharti.constraints.Roles;
import com.bharti.domain.Keynote;
import com.bharti.domain.KeynoteDetail;
import com.bharti.domain.LoginInfo;
import com.bharti.domain.SeoKeynote;
import com.bharti.domain.Subject;
import com.bharti.model.KeynoteModel;
import com.bharti.model.SeoModel;
import com.bharti.service.KeynoteDetailService;
import com.bharti.service.KeynoteService;
import com.bharti.service.LoginInfoService;
import com.bharti.service.SubjectService;

@Controller
public class AdminKeynoteController 
{
	@Autowired private LoginInfoService loginInfoService;
	@Autowired private KeynoteService keynoteService; 
	@Autowired private SubjectService subjectService; 
	@Autowired private KeynoteDetailService keynoteDetailService;
	
	private Logger logger = Logger.getLogger(AdminKeynoteController.class);
	
	@RequestMapping(value = "/adminKeynotes", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("sList", subjectService.getAllSubjectsList(0, 1000));
		map.addAttribute("sid", request.getParameter("sid"));
		System.out.println("Hello from admin keynotes ");
		return "keynotes";
	}
	
	@RequestMapping(value = "/getKeynoteList", method = RequestMethod.GET)
	public String getKeynoteList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String sid = request.getParameter("sid");
		String parent_kid = request.getParameter("parent_kid");
		
		if(sid != null && sid.trim().length() > 0) {
			try  {
				Subject subject = subjectService.getSubjectById(Integer.parseInt(sid));
				if(subject != null) {
					if(parent_kid != null && parent_kid.trim().length() > 0) {
						Keynote parent_kn = keynoteService.getKeynoteById(Integer.parseInt(parent_kid));
						if(parent_kn != null) {
							map.addAttribute("knList", keynoteService.getAllKeynoteList(subject.getSid(), parent_kn.getKid()));
							map.addAttribute("parent_kn", parent_kn);
						}
					} else {
						map.addAttribute("knList", keynoteService.getAllKeynoteList(subject.getSid()));
					}
					return "keynoteList";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "keynoteList";
	}
	
	
	
	
	@RequestMapping(value = "/adminAddKeynote", method = RequestMethod.GET)
	public String addKeynote(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("Hello from admin addKeynote");
		map.addAttribute("sub", request.getParameter("sub"));
		map.addAttribute("sList", subjectService.getSubjectsList(0, 1000));
		String sid = request.getParameter("sid");
		if(sid != null && sid.trim().length() > 0) {
			try {
				Subject subject = subjectService.getSubjectById(Integer.parseInt(sid));
				if(subject != null) {
					map.addAttribute("kList", keynoteService.getKeynoteList(subject.getSid()));
					map.addAttribute("sub", subject.getUrl());
					map.addAttribute("form_keynote", new KeynoteModel());
					return "addKeynote";
				}
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:adminKeynotes";
	}
	
	@RequestMapping(value = "/adminAddKeynote", method = RequestMethod.POST)
	public String addKeynote(@ModelAttribute(value = "form_keynote") @Valid KeynoteModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if (result.hasErrors()) {
				if(model.getSubject().getSid() == 0) {
					result.addError(new FieldError("form_keynote", "subject", model.getSubject() , false, new String[1],new String[1], "Please Select Subject !"));
					return "redirect:adminKeynotes";
				} else {
					map.addAttribute("kList", keynoteService.getKeynoteList(model.getSubject().getSid()));
				}
				
				if(request.isUserInRole(Roles.ROLE_PUBLISHER)) {
					map.addAttribute("sList", subjectService.getSubjectsList(0, 1000, principal.getName()));
				} else {
					map.addAttribute("sList", subjectService.getSubjectsList(0, 1000));
				}
				return "addKeynote";
		} else {
			
			String  btnType = request.getParameter("submit");
			System.out.println("Submited Buttom value : "+ btnType);
			
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			Keynote kn = new Keynote();
			
			kn.setKeynote(model.getKeynote());
			try  {
				kn.setDisplayOrder(Integer.parseInt(model.getDisplayOrder()));
			} catch (Exception e) {
				kn.setDisplayOrder(0);
			}
			if(model.getParent() != null) {
				Keynote parent_kn = keynoteService.getKeynoteById(model.getParent().getKid());
				if(parent_kn != null) {
					kn.setParent_keynote(parent_kn);
				}
			}
			
			LoginInfo loginInfo = this.loginInfoService.getLoginInfoByUserid(principal.getName());
			kn.setLoginInfo(loginInfo);
			kn.setSubject(model.getSubject());
			kn.setShowOnHomePage(model.isShowOnHomePage());
			String url = model.getKeynote().trim().replaceAll("[^a-zA-Z0-9]+","_");
			kn.setUrl(url.toLowerCase());
			kn.setCreateDate(dt);
			kn.setModifyDate(new Date());
			long kid = keynoteService.addKeynote(kn);
			
			kn.setKid(kid);

			KeynoteDetail knd = new KeynoteDetail();
			knd.setCreateDate(dt);
			knd.setDetail(model.getKnDetail());
			knd.setKeynote(kn);
			
			keynoteDetailService.addKeynoteDetail(knd);
			
			System.out.println("in else success ");
			Subject sub = subjectService.getSubjectById(model.getSubject().getSid());
			if(sub != null) {
				if(btnType != null && btnType.equals("Save")) {
					return "redirect:adminKeynotes?sid="+sub.getSid();
				}else {
					return "redirect:adminEditKeynote?kid="+kid;
				}
			}
			
			return "redirect:adminKeynotes";

		}
		
	}
	
	@RequestMapping(value = "/adminEditKeynote", method = RequestMethod.GET)
	public String editKeynote(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String kid = request.getParameter("kid");
		if(kid != null && kid.trim().length() > 0) {
			try {
				Keynote kn = keynoteService.getKeynoteById(Integer.parseInt(kid));
				if(kn != null && (request.isUserInRole(Roles.ROLE_ADMIN) || (request.isUserInRole(Roles.ROLE_PUBLISHER)
						&&  (kn.getLoginInfo() != null && kn.getLoginInfo().getUserid().equals(principal.getName()))))) {
					KeynoteModel model = new KeynoteModel();
					model.setKid(kn.getKid());
					model.setSubject(kn.getSubject());
					model.setParent(kn.getParent_keynote());
					model.setKeynote(kn.getKeynote());
					model.setShowOnHomePage(kn.isShowOnHomePage());
					model.setDisplayOrder(String.valueOf(kn.getDisplayOrder()));
					if(kn.getKeynoteDetail() != null) {
						model.setKnDetail(kn.getKeynoteDetail().getDetail());
					}
					map.addAttribute("sList", subjectService.getSubjectsList(0, 1000));
					map.addAttribute("kList", keynoteService.getKeynoteList(kn.getSubject().getSid()));
					map.addAttribute("sub", kn.getSubject().getUrl());
					
					map.addAttribute("form_keynote", model);
					
					SeoModel seoModel = new SeoModel();
					if(kn.getSeoKeynote() != null) {
						seoModel.setTitle(kn.getSeoKeynote().getTitle());
						seoModel.setDescription(kn.getSeoKeynote().getDescription());
						seoModel.setKeywords(kn.getSeoKeynote().getKeywords());
						seoModel.setImageUrl(kn.getSeoKeynote().getImageUrl());
						seoModel.setSeoId(kn.getSeoKeynote().getSeoId());
					}
					seoModel.setKeynote(kn);
					map.addAttribute("seo_keynote", seoModel);
					
					return "editKeynote";
				} else if(kn != null) {
					request.getSession().setAttribute("hasError", true);
					request.getSession().setAttribute("msg", "You are not authorized to change this article.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:adminKeynotes";
	}
	
	@RequestMapping(value = "/adminEditKeynote", method = RequestMethod.POST)
	public String adminEditKeynote(@ModelAttribute(value = "form_keynote") @Valid KeynoteModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if (result.hasErrors()) {
			if(model.getSubject().getSid() == 0) {
				result.addError(new FieldError("form_keynote", "subject", model.getSubject() , false, new String[1],new String[1], "Please Select Subject !"));
				return "redirect:adminKeynotes";
			} else {
				map.addAttribute("kList", keynoteService.getKeynoteList(model.getSubject().getSid()));
				System.out.println("in validation");
				List<Subject> sList = subjectService.getSubjectsList(0, 1000);
				map.addAttribute("sList", sList);
			}
			Keynote kn = this.keynoteService.getKeynoteById(model.getKid());
			SeoModel seoModel = new SeoModel();
			if(kn.getSeoKeynote() != null) {
				seoModel.setTitle(kn.getSeoKeynote().getTitle());
				seoModel.setDescription(kn.getSeoKeynote().getDescription());
				seoModel.setKeywords(kn.getSeoKeynote().getKeywords());
				seoModel.setImageUrl(kn.getSeoKeynote().getImageUrl());
				seoModel.setSeoId(kn.getSeoKeynote().getSeoId());
				seoModel.setKeynote(kn);
			}
			map.addAttribute("seo_keynote", seoModel);
			
			return "editKeynote";
		}
		else if(model.getKid() > 0) {
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			
			Keynote kn = keynoteService.getKeynoteById(model.getKid());
			if(kn != null) {
				String  btnType = request.getParameter("submit");
				System.out.println("Submited Buttom value : "+ btnType);
				
				kn.setKeynote(model.getKeynote());
				kn.setDisplayOrder(Integer.parseInt(model.getDisplayOrder()));
				kn.setShowOnHomePage(model.isShowOnHomePage());
				if(model.getParent() != null && model.getParent().getKid() > 0) {
					Keynote parent_kn = keynoteService.getKeynoteById(model.getParent().getKid());
					kn.setParent_keynote(parent_kn);
				}
				kn.setModifyDate(new Date());
				keynoteService.updateKeynote(kn);
				
				if(kn.getKeynoteDetail() != null) {
					KeynoteDetail knd = kn.getKeynoteDetail();
					knd.setDetail(model.getKnDetail());
					keynoteDetailService.updateKeynoteDetail(knd);
				} else {
					KeynoteDetail knd = new KeynoteDetail();
					knd.setDetail(model.getKnDetail());
					knd.setKeynote(kn);
					knd.setCreateDate(dt);
					keynoteDetailService.addKeynoteDetail(knd);
				}
				
				if(btnType != null && btnType.equals("Save")) {
					return "redirect:adminKeynotes?sid="+kn.getSubject().getSid();
				}else {
					return "redirect:adminEditKeynote?kid="+kn.getKid();
				}
			}
		}
			return "redirect:adminKeynotes";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/unpublishKeynote", method = RequestMethod.GET)
	@ResponseBody
	public String unpublishSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String kid = request.getParameter("kid");
		if(kid != null && kid.trim().length() > 0) {
			try  {
				Keynote keynote = keynoteService.getKeynoteById(Long.parseLong(kid));
				if(keynote != null && (request.isUserInRole(Roles.ROLE_ADMIN) || (request.isUserInRole(Roles.ROLE_PUBLISHER)
						&&  (keynote.getLoginInfo() != null && keynote.getLoginInfo().getUserid().equals(principal.getName()))))) {
					keynote.setPublishDate(null);
					keynoteService.updateKeynote(keynote);
					obj.put("success", true);
					return obj.toJSONString();
				} else if(keynote != null) {
					logger.info("You are not authorized to unpublish this article.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		obj.put("success", false);
		return obj.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/publishKeynote", method = RequestMethod.GET)
	@ResponseBody
	public String publishSubject(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String kid = request.getParameter("kid");
		if(kid != null && kid.trim().length() > 0) {
			try {
				Keynote keynote = keynoteService.getKeynoteById(Long.parseLong(kid));
				if(keynote != null && (request.isUserInRole(Roles.ROLE_ADMIN) || (request.isUserInRole(Roles.ROLE_PUBLISHER)
						&&  (keynote.getLoginInfo() != null && keynote.getLoginInfo().getUserid().equals(principal.getName()))))) {
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					keynote.setPublishDate(dt);
					keynoteService.updateKeynote(keynote);
					obj.put("success", true);
					return obj.toJSONString();
				} else if(keynote != null) {
					logger.info("You are not authorized to publish this article.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		obj.put("success", false);
		return obj.toJSONString();
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/adminDeleteKeynote", method = RequestMethod.GET)
	@ResponseBody
	public String adminDeleteKeynote(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String kid = request.getParameter("kid");
		if(kid != null && kid.trim().length() > 0) {
			try  {
				Keynote keynote = keynoteService.getKeynoteById(Long.parseLong(kid));
				if(keynote != null && (request.isUserInRole(Roles.ROLE_ADMIN) || (request.isUserInRole(Roles.ROLE_PUBLISHER)
						&&  (keynote.getLoginInfo() != null && keynote.getLoginInfo().getUserid().equals(principal.getName()))))) {
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					keynote.setDeleteDate(dt);
					keynoteService.updateKeynote(keynote);
					obj.put("success", true);
					return obj.toJSONString();
				} else if(keynote != null) {
					logger.info("You are not authorized to delete this article.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		obj.put("success", false);
		return obj.toJSONString();
	}
	
	
}
