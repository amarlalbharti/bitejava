package com.bharti.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bharti.constraints.KeynoteCompare;
import com.bharti.constraints.SeoConstants;
import com.bharti.domain.Keynote;
import com.bharti.domain.Subject;
import com.bharti.service.KeynoteService;
import com.bharti.service.SubjectService;
import com.bharti.utils.SeoUtils;

@Controller
public class SubjectController 
{
	@Autowired private SubjectService subjectService; 
	@Autowired private KeynoteService keynoteService; 
	
	private Logger logger = Logger.getLogger(AdminSubjectController.class);
	
	
	@RequestMapping(value = "/note/{subject}", method = RequestMethod.GET)
	public String index(@PathVariable("subject") String sub, ModelMap map, HttpServletRequest request, Principal principal) {
		System.out.println("Hello from admin dashboard " + sub);
		Subject subject = subjectService.getSubjectById(sub);
		if (subject != null) {
			List<Keynote> kList = keynoteService
					.getKeynoteList(subject.getSid());
			if (kList != null && !kList.isEmpty()) {
				map.addAttribute("kList", kList);
				map.addAttribute("subject", subject);

				Keynote keynote = keynoteService
						.getKeynoteWithChildByUrl(kList.get(0).getUrl());

				map.addAttribute("keynote", keynote);
				map.addAttribute("prevKn", null);
				if (kList.size() > 1) {
					map.addAttribute("nextKn", kList.get(1));
				}

				if (keynote.getParent_keynote() != null) {
					map.addAttribute("childKeynotes",
							keynoteService.getChildKeynoteList(
									keynote.getParent_keynote().getKid()));
				} else {
					map.addAttribute("childKeynotes", keynoteService
							.getChildKeynoteList(keynote.getKid()));
				}
				SeoUtils.setMetaData(map, keynote);
				return "subject";
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
				
				boolean notfind = true;
				
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
	
	
}
