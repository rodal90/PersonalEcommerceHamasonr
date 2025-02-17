package com.core.Hamasonr.controllerImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.core.Hamasonr.config.LanguageResourceBundleMessage;
import com.core.Hamasonr.controller.IHamaMasterController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HamaMasterControllerImpl implements IHamaMasterController {
	
	@Autowired
	private LanguageResourceBundleMessage languageResourceBundleMessage;

	@Override
	public void injectCommonAtrributesInHtmlPage(Principal principal, Model model, HttpServletRequest request) {
		
		model.addAttribute("username",principal.getName());
		model.addAttribute("userPicture", "");
		
		model.addAttribute("languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		model.addAttribute("requestURI", request.getRequestURI());
		
		log.warn("TRAZA: "+"languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		
		log.warn("Traza2: "+"requestURI", request.getRequestURI());
	}

}
