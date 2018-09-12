package com.bharti.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bharti.constraints.Validation;
import com.bharti.domain.Keynote;
import com.bharti.domain.SeoKeynote;
import com.bharti.model.KeynoteModel;
import com.bharti.model.SeoModel;
import com.bharti.service.KeynoteService;
import com.bharti.service.SeoKeynoteService;
import com.bharti.utils.Util;

@Controller
public class AdminSeoKeynoteController {
	
	@Autowired private KeynoteService keynoteService;
	@Autowired private SeoKeynoteService seoKeynoteService;
	
	@RequestMapping(value = "/adminSeoKeynotes", method = RequestMethod.GET)
	public String adminSeoKeynotes(ModelMap map, HttpServletRequest request, Principal principal)
	{
		List<Keynote> list = this.keynoteService.getRecentKeynotesForSeo(0, 10);
		map.addAttribute("knList", list);
		return "seoKeynote";
	}
	
	@RequestMapping(value = "/adminAddSeoKeynote", method = RequestMethod.GET)
	public String adminAddSeoKeynote(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String kid = request.getParameter("kid");
		if(Validation.isNumeric(kid)) {
			Keynote kn = this.keynoteService.getKeynoteById(Util.getNumericPositive(kid));
			if(kn != null) {
				
				SeoModel model = new SeoModel();
				model.setKeynote(kn);
				map.addAttribute("keynote", kn);
				map.addAttribute("seoModel", model);
				return "addSeoKeynote";
			}
		}
		return "redirect:adminSeoKeynotes";
	}
	
	@RequestMapping(value = "/adminAddSeoKeynote", method = RequestMethod.POST)
	public String addKeynote(@ModelAttribute(value = "seoModel") @Valid SeoModel model,BindingResult result, ModelMap map, HttpServletRequest request,Principal principal)
	{
		if (result.hasErrors()) {
			return "addSeoKeynote";
		} else {
			SeoKeynote seoKeynote = new SeoKeynote();
			seoKeynote.setTitle(model.getTitle());
			seoKeynote.setDescription(model.getDescription());
			seoKeynote.setKeywords(model.getKeywords());
			seoKeynote.setImageUrl(model.getImageUrl());
			seoKeynote.setKeynote(model.getKeynote());
			seoKeynote.setCreateDate(new Date());
			seoKeynote.setModifyDate(new Date());
			seoKeynote.setCreateBy(principal.getName());
			this.seoKeynoteService.addSeoKeynote(seoKeynote);
		}
		return "redirect:adminSeoKeynotes";
	}
}
