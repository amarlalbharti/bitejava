package com.bharti.filter;

import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bharti.domain.Registration;
import com.bharti.service.KeynoteService;
import com.bharti.service.RegistrationService;

public class IndexInterceptor implements HandlerInterceptor 
{
	@Autowired private RegistrationService registrationService; 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			Registration reg = (Registration) request.getSession().getAttribute("registration");
			if(reg == null) {
				Principal principal = request.getUserPrincipal();
				if(principal != null) {
//					System.out.println("user  : " + principal.getName());
					reg = registrationService.getRegistrationByUserid(principal.getName());
					if(reg != null) {
						request.getSession(true).setAttribute("registration", reg);
					}
				}
}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		System.out.println("pageTitle : " + request.getAttribute("pageTitle"));
//		System.out.println("pageDescription : " + request.getAttribute("pageDescription"));
//		System.out.println("pageKeywords : " + request.getAttribute("pageKeywords"));
//		System.out.println("pageAuthor : " + request.getAttribute("pageAuthor"));
		
	}

}
