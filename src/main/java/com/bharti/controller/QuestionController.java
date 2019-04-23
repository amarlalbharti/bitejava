package com.bharti.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bharti.constraints.SeoConstants;
import com.bharti.domain.Answers;
import com.bharti.domain.Attribute;
import com.bharti.domain.Question;
import com.bharti.domain.Tag;
import com.bharti.service.AnswersService;
import com.bharti.service.AttributeService;
import com.bharti.service.QuestionService;
import com.bharti.service.TagService;
import com.bharti.utils.SeoUtils;
import com.bharti.utils.Util;

@Controller
public class QuestionController 
{
	@Autowired private QuestionService questionService;
	@Autowired private AnswersService answersService;
	@Autowired private TagService tagService;
	@Autowired private AttributeService attributeService;
	
	private Logger logger = Logger.getLogger(QuestionController.class);
	
	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String questions(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Get asked questions");
		StringBuilder sb = new StringBuilder();
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("featured")) {
			map.addAttribute("qList", questionService.getFeaturedQuestions(0, 20));
			map.addAttribute("mode", "featured");
			sb.append("Featured questions");
			
		} else if (mode != null && mode.equalsIgnoreCase("most")) {
			map.addAttribute("qList", questionService.getMostViewedQuestions(0, 20));
			map.addAttribute("mode", "most");
			sb.append("Mostly asked questions");
			
		} else {
			map.addAttribute("qList", questionService.getRecentQuestions(0, 20));
			map.addAttribute("mode", "recent");
			sb.append("Recently asked questions");
		}
		List<Tag> tags = tagService.getMostCommonTags(10);
		map.addAttribute("topTags", tags);
		String tagsStr = "";
		for(Tag tag : tags) {
			tagsStr += ", "+tag.getTag();
		}
		map.addAttribute("pageKeywords", !tagsStr.equals("") ? tagsStr.substring(2) : "");
		map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
		map.addAttribute("pageDescription", "Bitejava tutorials provides the most frequently asked questions , recently asked questions and featured questions based on views");
		map.addAttribute("pageTitle", sb.toString()+SeoConstants.SEO_POST_TITLE);
		
		
		return "viewQuestions";
	}
	
	@RequestMapping(value = "/questions/{qid}/{ques}", method = RequestMethod.GET)
	public String answer(@PathVariable("qid") String qid, ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Get Answers of question with qid :" + qid);
		try {
			Question que = questionService.getQuestionById(Long.parseLong(qid));
			if(que != null) {
				map.addAttribute("que", que);
				map.addAttribute("qList", questionService.getRecentAdminQuestions(0, 20));
				questionService.increamentQuestionViewsByQid(que.getQid());
				map.addAttribute("topTags", tagService.getMostCommonTags(10));
				logger.info("Returning to jsp page with question :" + que.getQustion());
				if(que.getImage() == null) {
					Attribute attribute = attributeService.getAttribute("commonSeoImage");
					if(attribute != null) {
						que.setImage(attribute.getAttributeValue());
					}
				}
				SeoUtils.setMetaData(map, que);
				return "viewAnswer";
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	@RequestMapping(value = "/questions/tagged/{tag}", method = RequestMethod.GET)
	public String taggedQuestions(@PathVariable("tag") String tagString,ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Get The tagged questions Tag : " + tagString);
		if(tagString != null && tagString.trim().length() > 0)
		{
			Tag tag = tagService.getTag(tagString);
			if(tag != null)
			{
				map.addAttribute("qList", questionService.getAdminQuestionsByTag(tag.getTid(), 0, 20));
				map.addAttribute("topTags", tagService.getMostCommonTags(10));
				map.addAttribute("mode", "tagged");
				map.addAttribute("tag", tag);
				
				map.addAttribute("pageTitle", tag.getTag()+SeoConstants.SEO_POST_TITLE);
				map.addAttribute("pageKeywords", tag.getTag());
				map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
				map.addAttribute("pageDescription", tag.getTagDetails()!= null ? tag.getTagDetails():"" );
				
				logger.info("Returning to the jsp page with question list tagged :" + tagString);
				return "viewQuestions";
			}
		}
		return "redirect:/error";
	}
}
