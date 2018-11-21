package com.bharti.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.Registration;
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

import com.bharti.domain.Tag;
import com.bharti.service.TagService;

@Controller
public class AdminTagController {

	@Autowired private TagService tagService; 
	private Logger logger = Logger.getLogger(AdminTagController.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchTags", method = RequestMethod.GET)
	@ResponseBody
	public String searchTags(ModelMap map, HttpServletRequest request, Principal principal) {
		String search_text = request.getParameter("q");
		JSONArray array = new JSONArray();
		logger.info("search_text : " + search_text);
		if(search_text != null && search_text.trim().length() > 1) {
			List<Tag> list = tagService.searchTag(search_text);
			if(list != null && !list.isEmpty()) {
				for(Tag reg : list) {
					JSONObject r = new JSONObject();
					r.put("tag", reg.getTag());
					r.put("tid", reg.getTid());
					array.add(r);
				}
				
			}
		}
		logger.info("JSON : " + array.toJSONString());
		return array.toJSONString();
	}
	
}
