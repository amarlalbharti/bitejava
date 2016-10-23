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

	
	@Autowired private KeynoteService keynoteService; 
	@Autowired private RegistrationService registrationService; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try
		{
			Registration reg = (Registration) request.getSession().getAttribute("registration");
			if(reg == null)
			{
				Principal principal = request.getUserPrincipal();
				System.out.println("principal : " + principal);
				if(principal != null)
				{
					System.out.println("user  : " + principal.getName());
					reg = registrationService.getRegistrationByUserid(principal.getName());
					if(reg != null)
					{
						request.getSession(true).setAttribute("registration", reg);
					}
				}
			}
			String path_url = (String)request.getSession().getAttribute("path_url");
			if(path_url == null || path_url.trim().length() <= 0)
			{
				String url = request.getRequestURL().toString();
				int port = request.getLocalPort();
				
				if(url != null && url.trim().length() > 0)
				{
					int index =url.indexOf(""+port) ;
					String path = url.substring(0, index);
					request.getSession().setAttribute("path_url", path+port+"/bitejava");
					System.out.println("Path : " + path+port+"/bitejava");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
