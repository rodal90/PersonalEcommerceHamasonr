package com.core.Hamasonr.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.Hamasonr.data.model.HamaProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface IHamaProviderController {
	
	 public String providerListGet(Principal principal, Model model, HttpServletRequest request);

	public String providerViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerAddGet(Principal principal, Model model, HttpServletRequest request);

	public String providerUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerUpdatePost(@Valid HamaProvider provider, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

	public String providerAddPost(@Valid HamaProvider provider, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

}
