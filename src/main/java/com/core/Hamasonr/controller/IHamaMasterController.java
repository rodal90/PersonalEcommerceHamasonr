package com.core.Hamasonr.controller;

import java.security.Principal;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface IHamaMasterController {
	
	public void injectCommonAtrributesInHtmlPage(			
			Principal principal, 
			Model model, 
			HttpServletRequest request);

}
