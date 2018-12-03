package com.bharti.utils;

import java.util.Iterator;

import org.springframework.ui.ModelMap;

import com.bharti.constraints.ProjectConfig;
import com.bharti.constraints.SeoConstants;
import com.bharti.domain.Answers;
import com.bharti.domain.Keynote;
import com.bharti.domain.Question;
import com.bharti.domain.Tag;

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
			}else if(obj instanceof Question) {
				
				Question q = (Question)obj;
				
				map.addAttribute("pageTitle", q.getQustion()+SeoConstants.SEO_POST_TITLE);
				
				StringBuilder seoSb = new StringBuilder();
				Iterator<Answers> it = q.getAnswers().iterator();
				if(it.hasNext()){
					Answers answer = it.next();
					seoSb.append(Util.html2text(answer.getAnswer()));
				}
				
				map.addAttribute("pageDescription", seoSb.substring(0, 255));
				
				String tags = "";
				Iterator<Tag> itTag = q.getTags().iterator();
				while(itTag.hasNext()){
					Tag tag = itTag.next();
					tags += ", "+tag.getTag();
				}
				map.addAttribute("pageKeywords", !tags.equals("") ? tags.substring(2) : "");
				
				map.addAttribute("pageImageUrl", q.getImage() != null ? q.getImage() : "/bj_uploads/uploadedfiles/45/default_image.jpg");
				
				String que = q.getQustion();
				que = que.replaceAll(" ", "-");
				map.addAttribute("pageUrl", ProjectConfig.SITE_URL+"/questions/"+q.getQid()+"/"+que);
			}
		}else {
			map.addAttribute("pageTitle", SeoConstants.SEO_DEFAULT_TITLE);
		}
		map.addAttribute("pageAuthor", SeoConstants.SEO_DEFAULT_AUTHOR);
		
	}
	
}
