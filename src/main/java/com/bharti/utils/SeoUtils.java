package com.bharti.utils;

import org.springframework.ui.ModelMap;

import com.bharti.constraints.ProjectConfig;
import com.bharti.constraints.SeoConstants;
import com.bharti.domain.Keynote;

public class SeoUtils {
	public static void setMetaData(ModelMap map, Object obj) {
		if(obj != null) {
			if(obj instanceof Keynote) {
				Keynote kn = (Keynote)obj;
				if(kn.getSeoKeynote()!= null) {
					map.addAttribute("pageTitle", kn.getSeoKeynote().getTitle()+ SeoConstants.SEO_POST_TITLE);
					map.addAttribute("pageDescription", kn.getSeoKeynote().getDescription().length() > 200 ? kn.getSeoKeynote().getDescription().substring(0, 195)+"..." : kn.getSeoKeynote().getDescription());
					map.addAttribute("pageKeywords", kn.getSeoKeynote().getKeywords());
					map.addAttribute("pageImageUrl", kn.getSeoKeynote().getImageUrl());
					
				}else if (kn != null) {
					map.addAttribute("pageTitle", kn.getKeynote()+ SeoConstants.SEO_POST_TITLE);
				}
				map.addAttribute("pageUrl", ProjectConfig.SITE_URL+"/note/"+kn.getSubject().getSubject()+"/"+kn.getUrl());
			}
		}else {
			map.addAttribute("pageTitle", SeoConstants.SEO_DEFAULT_TITLE);
		}
		map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
		
	}
	
}
