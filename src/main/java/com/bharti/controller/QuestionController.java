package com.bharti.controller;

import java.security.Principal;
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
import com.bharti.domain.Keynote;
import com.bharti.domain.Question;
import com.bharti.domain.Subject;
import com.bharti.domain.Tag;
import com.bharti.service.AnswersService;
import com.bharti.service.QuestionService;
import com.bharti.service.TagService;

@Controller
public class QuestionController 
{
	@Autowired private QuestionService questionService;
	@Autowired private AnswersService answersService;
	@Autowired private TagService tagService;
	
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
			sb.append("Featured questions"+SeoConstants.SEO_POST_TITLE);
			
		} else if (mode != null && mode.equalsIgnoreCase("most")) {
			map.addAttribute("qList", questionService.getMostViewedQuestions(0, 20));
			map.addAttribute("mode", "most");
			sb.append("Mostly asked questions"+SeoConstants.SEO_POST_TITLE);
			
		} else {
			map.addAttribute("qList", questionService.getRecentQuestions(0, 20));
			map.addAttribute("mode", "recent");
			sb.append("Recently asked questions"+SeoConstants.SEO_POST_TITLE);
		}
		
		map.addAttribute("topTags", tagService.getMostCommonTags(10));
		map.addAttribute("pageKeywords", SeoConstants.SEO_DEFAULT_KEYWORDS);
		map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
		map.addAttribute("pageDescription", SeoConstants.SEO_DEFAULT_DESCRIPTION);
		map.addAttribute("pageTitle", sb.toString());
		
		
		return "viewQuestions";
	}
	
	@RequestMapping(value = "/questions/{qid}/{ques}", method = RequestMethod.GET)
	public String answer(@PathVariable("qid") String qid, ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Get Answers of question with qid :" + qid);
		try 
		{
			Question que = questionService.getQuestionById(Long.parseLong(qid));
			if(que != null)
			{
				map.addAttribute("que", que);
				map.addAttribute("qList", questionService.getRecentAdminQuestions(0, 20));
				questionService.increamentQuestionViewsByQid(que.getQid());
				map.addAttribute("topTags", tagService.getMostCommonTags(10));
				logger.info("Returning to jsp page with question :" + que.getQustion());
				
				map.addAttribute("pageKeywords", SeoConstants.SEO_DEFAULT_KEYWORDS);
				map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
				map.addAttribute("pageDescription", SeoConstants.SEO_DEFAULT_DESCRIPTION);
				map.addAttribute("pageTitle", que.getQustion()+SeoConstants.SEO_POST_TITLE);
				
				return "viewAnswer";
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "redirect:error";
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
				
				map.addAttribute("pageKeywords", SeoConstants.SEO_DEFAULT_KEYWORDS);
				map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
				map.addAttribute("pageDescription", SeoConstants.SEO_DEFAULT_DESCRIPTION);
				map.addAttribute("pageTitle", tag.getTag()+SeoConstants.SEO_POST_TITLE);
				
				logger.info("Returning to the jsp page with question list tagged :" + tagString);
				return "viewQuestions";
			}
		}
		return "redirect:/error";
	}
}
