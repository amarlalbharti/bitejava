package com.bharti.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bharti.constraints.Roles;
import com.bharti.domain.Keynote;
import com.bharti.service.KeynoteDetailService;
import com.bharti.service.KeynoteService;
import com.bharti.service.QuestionService;
import com.bharti.service.RegistrationService;
import com.bharti.service.SubjectService;

@Controller
public class AdminConroller
{
	@Autowired private KeynoteService keynoteService; 
	@Autowired private SubjectService subjectService; 
	@Autowired private RegistrationService registrationService; 
	@Autowired private QuestionService questionService; 
	
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dashboard/widgets", method = RequestMethod.GET)
	@ResponseBody
	public String dashboardWidget(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject resp = new JSONObject();
		try {
			
			JSONArray resArray =  new JSONArray();
			
			JSONObject obj = new JSONObject();
			Long sCount = subjectService.countSubjects();
			obj.put("count", sCount);
			obj.put("title", "Subjects");
			resArray.add(obj);
			
			obj = new JSONObject();
			Long kCount = keynoteService.countKeynote();
			obj.put("count", kCount);
			obj.put("title", "Keynotes");
			resArray.add(obj);
			
			obj = new JSONObject();
			Long uCount = registrationService.countEmployees();
			obj.put("count", uCount);
			obj.put("title", "Users");
			resArray.add(obj);
			
			obj = new JSONObject();
			Long qCount = questionService.countAdminQuestions();
			obj.put("count", qCount);
			obj.put("title", "Questions");
			resArray.add(obj);
			
			resp.put("data", resArray);
			resp.put("success", true);
			return obj.toJSONString();
			
		}catch (Exception e) {
			logger.error("Exception in /dashboard/widgets : ", e);
		}
		resp.put("msg", "Some Error Occured !");
		resp.put("success", false);
		return resp.toJSONString();
	}
	
}
