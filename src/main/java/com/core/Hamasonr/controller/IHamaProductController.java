package com.core.Hamasonr.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.Hamasonr.data.model.HamaProduct;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public interface IHamaProductController {
	
	
	 @GetMapping("/product/list")
	    String productListGet(Principal principal, Model model, HttpServletRequest request);

	    @GetMapping("/product/view/{id}")
	    String productViewGet(@PathVariable("id") Long id, Model model);

	    @GetMapping("/product/add")
	    String productAddGet(Model model);

	    @PostMapping("/product/add")
	    String productAddPost(@Valid HamaProduct product, BindingResult bindingResult, Model model);

	    @GetMapping("/product/edit/{id}")
	    String productEditGet(@PathVariable("id") Long id, Model model);

	    @PostMapping("/product/edit")
	    String productEditPost(@Valid HamaProduct product, BindingResult bindingResult);

	    @GetMapping("/product/delete/{id}")
	    String productDelete(@PathVariable("id") Long id);

	    @GetMapping("/product/search")
	    String searchProducts(@RequestParam("name") String name, Model model);
	
	
	
	
	

}
