package com.core.Hamasonr.controllerImpl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.Hamasonr.controller.IHamaUserController;
import com.core.Hamasonr.data.model.HamaProvider;
import com.core.Hamasonr.data.model.HamaUser;
import com.core.Hamasonr.data.repository.IHamaRoleRepository;
import com.core.Hamasonr.service.IHamaProviderService;
import com.core.Hamasonr.service.IHamaUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HamaUserControllerImpl extends HamaMasterControllerImpl implements IHamaUserController {

	@Autowired
	private IHamaUserService hamaUserService;
	
    @Override
	@GetMapping({"/user/viewGet/{id}"})
	public String userViewGet(@PathVariable("id") String id, Principal principal, Model model,
			HttpServletRequest request) {
		
		System.out.println("TRAZA USER VIEW GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("user",this.hamaUserService.findById(id).get());
		
		return "users/userView";
	}
	
    @Override
	@GetMapping({"/userListGet"})
	public String userListGet(Principal principal, Model model, HttpServletRequest request) {
		   log.info("TRAZA USER LIST GET");
		   
		   this.injectCommonAtrributesInHtmlPage(principal, model, request);
		   
		   model.addAttribute("userList",this.hamaUserService.findall()
				   .stream()
				   .sorted(Comparator.comparing(HamaUser::getFullname))
				   .collect(Collectors.toList()));
		   
		   
		return "users/userList";
	}
	
	
    @Override
	@Secured({"MANAGER"})
	@GetMapping({"/user/deleteGet/{id}"})
	public String providerDeleteGet(@PathVariable("id") String id, Principal principal, Model model,
			HttpServletRequest request) {
		
		log.info("TRAZA USER DELETE GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("user",this.hamaUserService.findById(id).get());
		
		return "users/userDelete";
		
	}
	

    @Override
	@GetMapping({"/user/updateGet/{id}"})
	public String userUpdateGet(@PathVariable("id") String id,Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA USER UPDATE GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("user", this.hamaUserService.findById(id).get());
		
		return "users/userUpdate";
	}
	
    @Override
	@PostMapping({"/user/updatePost"})
	public String userUpdatePost(@Valid HamaUser user, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request){
		  
		log.info("TRAZA USER UPDATE POST");
		
		if(bindingResult.hasErrors()){
			log.error("El formulario de User tiener errores" + bindingResult.getAllErrors());
			
			return "redirect:/user/updateGet/" + user.getUsername();
			
		} else {
			
			log.info("Formulario correcto: " + user);
			
			this.hamaUserService.save(user);
			
			return "redirect:/userListGet";
			
		}
			
	}
	
    @Override
	@GetMapping({"/user/addGet"})
	public String userAddGet(Principal principal, Model model, HttpServletRequest request) {
		log.info("TRAZA USER ADD GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("user", this.hamaUserService.newUser());
		
		return "users/userAdd";
		
	}
	
    @Override
	@PostMapping({"/user/addPost"})
	public String userAddPost(@Valid HamaUser user, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request){
		log.info("TRAZA USER ADD POST");
		
		if(bindingResult.hasErrors()) {
			
			log.error("El formulario de user tiene errores" + bindingResult.getAllErrors());
			
			return "users/userAdd";
			
		}else {
			log.info("Formulario correcto: " + user);
			
			this.hamaUserService.save(user);
			
			return "redirect:/userListGet";
		}
		
	}
	

}
