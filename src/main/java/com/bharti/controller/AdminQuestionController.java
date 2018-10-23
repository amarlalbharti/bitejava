package com.bharti.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bharti.constraints.StaticValues;
import com.bharti.domain.Answers;
import com.bharti.domain.Question;
import com.bharti.domain.Tag;
import com.bharti.model.KeynoteModel;
import com.bharti.model.QuestionAnswerModel;
import com.bharti.service.AnswersService;
import com.bharti.service.KeynoteService;
import com.bharti.service.QuestionService;
import com.bharti.service.SubjectService;
import com.bharti.service.TagService;

@Controller
public class AdminQuestionController 
{	
	@Autowired private QuestionService questionService; 
	@Autowired private AnswersService answersService; 
	@Autowired private TagService tagService; 
	
	private Logger logger = Logger.getLogger(AdminQuestionController.class);
	
	@RequestMapping(value = "/adminQuestions", method = RequestMethod.GET)
	public String adminQuestions(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From admin recent questions");
		return "questions";
	}
	
	@RequestMapping(value = "/getAdminQuestionList", method = RequestMethod.GET)
	public String getQuestionList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String pn = request.getParameter("pn");
		logger.info("Questions by admin for page : "+ pn);
		int pageno = 1;
		if(pn != null && pn.trim().length() > 0)
		{
			try
			{
				pageno = Integer.parseInt(pn);
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
		}
		map.addAttribute("qList", questionService.getRecentAdminQuestions((pageno-1)*StaticValues.rpp, StaticValues.rpp));
		map.addAttribute("total_count", (int)questionService.countAdminQuestions());
		map.addAttribute("rpp", StaticValues.rpp);
		map.addAttribute("pn", pageno);
		logger.info("Sending ajax response");
		return "questionList";
	}
	
	
	
	@RequestMapping(value = "/adminAskQuestion", method = RequestMethod.GET)
	public String adminAskQuestion(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("From admin ask question");
		map.addAttribute("queForm", new QuestionAnswerModel());
		return "askQuestion";
	}
	
	
	
	@RequestMapping(value = "/adminAskQuestion", method = RequestMethod.POST)
	public String adminAskQuestion(@ModelAttribute(value = "queForm") @Valid QuestionAnswerModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if (result.hasErrors() || model.getTags() == null || model.getTags().isEmpty())
		{
			logger.error("POst ask question has error : " + result.toString());
			return "askQuestion";
		}
		else
		{
			try
			{
				Date date = new Date();
				java.sql.Date dt = new java.sql.Date(date.getTime());
				
				Question q = new Question();
				
				q.setQustion(model.getQuestion());
				q.setUserType("admin");
				
				Set<String> tSet = model.getTags();
				
				List<Tag> tags = tagService.getTagList(model.getTags());
				for(Tag tag : tags)
				{
					logger.info("Tag already in table : " + tag.getTag());
					tSet.remove(tag.getTag());
				}
				
				for(String tag : tSet)
				{
					logger.info(" Adding new tag : " + tag);
					Tag t = new Tag();
					t.setTag(tag.toLowerCase());
					long tid = tagService.addTag(t);
					
					t.setTid(tid);
					tags.add(t);
				}
				Set<Tag> set = new HashSet<>();
				set.addAll(tags);
				q.setTags(set);
				String submit = request.getParameter("submit");
				if(submit != null && submit.equals("Save And Publish"))
				{
					logger.info("Saving and publishing button clicked ");
					q.setPublishDate(dt);
				}
				q.setCreateDate(dt);
				q.setModifyDate(dt);
				
				long qid = questionService.addQuestion(q);
				q.setQid(qid);
				
				Answers ans = new Answers();
				ans.setAnswer(model.getAnswer());
				ans.setCreateDate(dt);
				ans.setModifyDate(dt);
				ans.setQuestion(q);
				
				answersService.addAnswer(ans);
				
				logger.info("Question successfully added");
				map.addAttribute("add_que", "success");
				return "redirect:adminQuestions";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("Failed to save question error : " + e);
			}
			
		}
		map.addAttribute("add_que", "failed");
		return "redirect:adminQuestions";
	}
	
	@RequestMapping(value = "/adminEditQuestion", method = RequestMethod.GET)
	public String adminEditQuestion(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String qid = request.getParameter("qid");
		logger.info("Edit question qid : "+ qid);
		if(qid != null && qid.trim().length() > 0)
		{
			try
			{
				Question que = questionService.getQuestionById(Long.parseLong(qid));
				if(que != null)
				{
					logger.info("Question foumd in database, Question  " + que.getQustion());
					QuestionAnswerModel model = new  QuestionAnswerModel();
					
					model.setQuestion(que.getQustion());
					model.setQid(que.getQid());
					
					Set<String> tags = new HashSet<>();
					Iterator<Tag> it = que.getTags().iterator();
					while(it.hasNext())
					{
						tags.add(it.next().getTag());
					}
					model.setTags(tags);
					
					if(que.getAnswers().size() > 0)
					{
						Iterator<Answers> ait = que.getAnswers().iterator();
						if(ait.hasNext())
						{
							model.setAnswer(ait.next().getAnswer());
						}
					}
					
					map.addAttribute("tags", tags);
					map.addAttribute("queForm", model);
					logger.info("Sending to edit form !");
					return "editQuestion";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("Something wrong to edit this question id" + qid + "/n",  e);
			}
			
		}
		return "redirect:error";
	}
	
	@RequestMapping(value = "/adminEditQuestion", method = RequestMethod.POST)
	public String adminEditQuestion(@ModelAttribute(value = "queForm") @Valid QuestionAnswerModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if (result.hasErrors() || model.getQid() <= 0)
		{
			logger.error("Post method for update question has error : " + result.toString());
			map.addAttribute("tags", model.getTags());
			return "askQuestion";
		}
		else
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			
			Question q = questionService.getQuestionById(model.getQid());
			if(q != null)
			{
				logger.info("Question found in database for update");
				q.setQustion(model.getQuestion());
				
				Set<String> tSet = model.getTags();
				
				List<Tag> tags = tagService.getTagList(model.getTags());
				for(Tag tag : tags)
				{
					tSet.remove(tag.getTag());
				}
				if(tSet != null && !tSet.isEmpty())
				{
					for(String tag : tSet)
					{
						logger.info(" adding new tag : " + tag);
						Tag t = new Tag();
						t.setTag(tag.toLowerCase());
						long tid = tagService.addTag(t);
						
						t.setTid(tid);
						tags.add(t);
					}
				}
				Set<Tag> set = new HashSet<>();
				set.addAll(tags);
				q.setTags(set);
				String submit = request.getParameter("submit");
				if(q.getPublishDate() == null && submit != null && submit.equals("Update And Publish"))
				{
					q.setPublishDate(dt);
				}
				q.setModifyDate(dt);
				
				questionService.updateQuestion(q);
				
				
				if(q.getAnswers().size() > 0)
				{
					Iterator<Answers> ait = q.getAnswers().iterator();
					if(ait.hasNext())
					{
						Answers ans= ait.next();
						ans.setAnswer(model.getAnswer());
						ans.setModifyDate(dt);
						answersService.updateAnswer(ans);
					}
				}
				else
				{
					Answers ans= new Answers();
					ans.setAnswer(model.getAnswer());
					ans.setQuestion(q);
					ans.setModifyDate(dt);
					ans.setCreateDate(dt);
					answersService.addAnswer(ans);
				}
			
				
				
				logger.info("Question updated successfully");
				map.addAttribute("edit_que", "success");
				return "redirect:adminQuestions";
			}
			
		}
		map.addAttribute("edit_que", "failed");
		return "redirect:adminQuestions";
	}
	
}
