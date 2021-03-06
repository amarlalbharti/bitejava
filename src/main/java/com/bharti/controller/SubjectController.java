package com.bharti.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bharti.constraints.KeynoteCompare;
import com.bharti.constraints.SeoConstants;
import com.bharti.domain.Comments;
import com.bharti.domain.Keynote;
import com.bharti.domain.Registration;
import com.bharti.domain.Subject;
import com.bharti.service.CommentService;
import com.bharti.service.KeynoteService;
import com.bharti.service.RegistrationService;
import com.bharti.service.SubjectService;
import com.bharti.utils.CommentParser;
import com.bharti.utils.SeoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SubjectController 
{
	@Autowired private SubjectService subjectService; 
	@Autowired private KeynoteService keynoteService; 
	@Autowired private CommentService commentService; 
	@Autowired private RegistrationService registrationService; 
	
	private Logger logger = Logger.getLogger(AdminSubjectController.class);
	
	
	@RequestMapping(value = "/note/{subject}", method = RequestMethod.GET)
	public String index(@PathVariable("subject") String sub, ModelMap map, HttpServletRequest request, Principal principal) {
		System.out.println("Hello from admin dashboard " + sub);
		Subject subject = subjectService.getSubjectById(sub);
		if (subject != null) {
			List<Keynote> kList = keynoteService.getKeynoteList(subject.getSid());
			if (kList != null && !kList.isEmpty()) {

				Keynote keynote = keynoteService.getKeynoteWithChildByUrl(kList.get(0).getUrl());

				return "redirect:/note/"+subject.getUrl()+"/"+keynote.getUrl();
			}
		}
		return "redirect:/error";
	}

	@RequestMapping(value = "/note/{subject}/{keynote}", method = RequestMethod.GET)
	public String getKeynote(@PathVariable("subject") String sub, @PathVariable("keynote") String keynote, ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		System.out.println("Hello from admin dashboard " + sub);
		Subject subject =subjectService.getSubjectById(sub);
		if(subject != null)
		{
			Keynote kn = keynoteService.getKeynoteWithChildByUrl(keynote);
			if(kn != null) {
				List<Keynote> kList = keynoteService.getKeynoteList(kn.getSubject().getSid());
				map.addAttribute("kList", kList);
				map.addAttribute("subject", subject);
				map.addAttribute("keynote", kn);
				System.out.println("##########################"+kn.getLoginInfo().getUserid());
				boolean notfind = true;
				if(kn.getLoginInfo() != null) {
					Registration reg = this.registrationService.getRegistrationByUserid(kn.getLoginInfo().getUserid());
					
					map.addAttribute("author", reg);
				}
				
				if(kn.getParent_keynote() != null) {
					List<Keynote> childs = keynoteService.getChildKeynoteList(kn.getParent_keynote().getKid());
					if(childs != null && !childs.isEmpty()) {
						map.addAttribute("childKeynotes", childs);
						if(childs.contains(kn)) {
							notfind = false;
							System.out.println("Kn find");
							int index = childs.indexOf(kn);
							if(index > 0) {
								map.addAttribute("prevKn", childs.get(index-1));
							} else {
								int par_index = kList.indexOf(kn.getParent_keynote());
								if(par_index > 0) {
									map.addAttribute("prevKn", kList.get(par_index-1));
								} else {
									map.addAttribute("prevKn", kList.get(par_index));
								}
							}
							
							
							if(index < childs.size()-1) {
								map.addAttribute("nextKn", childs.get(index+1));
							} else {
								int par_index = kList.indexOf(kn.getParent_keynote());
								if(par_index < kList.size()-1) {
									map.addAttribute("nextKn", kList.get(par_index+1));
								}
							}
						}
					}
				} else {
					map.addAttribute("childKeynotes", keynoteService.getChildKeynoteList(kn.getKid()));
				}
				
				if(notfind && kList.contains(kn)) {
					System.out.println("Kn find");
					int index = kList.indexOf(kn);
					if(index > 0) {
						map.addAttribute("prevKn", kList.get(index-1));
					}
					if(index < kList.size()-1) {
						map.addAttribute("nextKn", kList.get(index+1));
					}
				}
				SeoUtils.setMetaData(map, kn);
				return "subject";
			}
		}
		return "redirect:/error";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/article/comments/{kid}", method = RequestMethod.GET)
	public @ResponseBody String getArticleCommentsKeynote(@PathVariable("kid") Long kid, ModelMap map, HttpServletRequest request, Principal principal){
		JSONObject resp = new JSONObject();
		try {
			List<Comments> commentList = commentService.getCommentByKeynote(kid);
			if(commentList != null && !commentList.isEmpty()) {
				resp.put("data", CommentParser.parseComment(commentList));
				resp.put("statusCode", 200);
				resp.put("status", "success");
			}
		}catch (Exception e) {
			resp.put("statusCode", 215);
			resp.put("status", "failed");
			logger.error("Error occured :", e);
		}
		return resp.toJSONString();
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addArticleComment/{kid}", method = RequestMethod.POST)
	public @ResponseBody String addComment(@PathVariable Long kid, ModelMap map, HttpServletRequest request, Principal principal){
		JSONObject obj = new JSONObject();
		try {
			String com = request.getParameter("comment");
			Keynote kn = this.keynoteService.getKeynoteById(kid);
			if(kn != null && kn.getSubject() != null) {
				Comments comment = new Comments();
				comment.setKeynote(kn);
				comment.setComment(com);
				if(principal != null) {
					Registration reg = this.registrationService.getRegistrationByUserid(principal.getName());
					if(reg != null) {
						comment.setCreateBy(principal.getName());
						comment.setName(reg.getName());
						comment.setIsAuthenticated(Boolean.TRUE);
					}
				}else {
					comment.setCreateBy(request.getParameter("email"));
					comment.setName(request.getParameter("name"));
				}
				comment.setCreateDate(new Date());
				this.commentService.addComment(comment);
				obj.put("status", Boolean.TRUE);
				obj.put("statusCode", 200);
			}
		}catch (Exception e) {
			obj.put("status", Boolean.FALSE);
			obj.put("statusCode", 400);
			obj.put("msg", "Some error occured !!");
			
			logger.error("Error occured :", e);
		}
		return obj.toJSONString();
	}
}
