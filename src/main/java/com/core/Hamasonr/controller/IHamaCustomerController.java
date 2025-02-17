package com.core.Hamasonr.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.Hamasonr.data.model.HamaCustomer;
import com.core.Hamasonr.data.model.HamaProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface IHamaCustomerController {
	
	 public String customerListGet(Principal principal, Model model, HttpServletRequest request);

	public String customerViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String customerDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String customerDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);

	public String customerAddGet(Principal principal, Model model, HttpServletRequest request);

	public String customerUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String customerUpdatePost(@Valid HamaCustomer customer, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

	public String customerAddPost(@Valid HamaCustomer customer, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

}
