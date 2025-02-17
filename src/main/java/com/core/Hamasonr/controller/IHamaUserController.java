package com.core.Hamasonr.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.Hamasonr.data.model.HamaUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface IHamaUserController {

	String userViewGet(String id, Principal principal, Model model, HttpServletRequest request);

	String userListGet(Principal principal, Model model, HttpServletRequest request);

	String providerDeleteGet(String id, Principal principal, Model model, HttpServletRequest request);

	String userUpdateGet(String id, Principal principal, Model model, HttpServletRequest request);

	String userUpdatePost(@Valid HamaUser user, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

	String userAddGet(Principal principal, Model model, HttpServletRequest request);

	String userAddPost(@Valid HamaUser user, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

}
