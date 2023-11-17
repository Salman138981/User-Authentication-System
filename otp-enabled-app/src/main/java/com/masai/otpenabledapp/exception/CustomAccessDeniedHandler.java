package com.masai.otpenabledapp.exception;



import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
    public void handle(
      HttpServletRequest request,
      HttpServletResponse response, 
      AccessDeniedException exc) throws IOException, ServletException {
        
Authentication auth 
       = SecurityContextHolder.getContext().getAuthentication();
        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }


	
}
