package com.core.Hamasonr.controllerImpl;

import java.security.Principal;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.Hamasonr.controller.IHamaProviderController;
import com.core.Hamasonr.data.model.HamaProvider;
import com.core.Hamasonr.service.IHamaProviderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HamaProviderControllerImpl extends HamaMasterControllerImpl implements IHamaProviderController {

	
	@Autowired
	private IHamaProviderService hamaProviderService;
	
    @Override
	@GetMapping({"/provider/viewGet/{id}"})
	public String providerViewGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		
		System.out.println("TRAZA PROVIDER VIEW GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("provider",this.hamaProviderService.findById(id).get());
		
		return "provider/providerView";
	}
	
    @Override
	@GetMapping({"/providerListGet"})
	public String providerListGet(Principal principal, Model model, HttpServletRequest request) {
		   log.info("TRAZA PROVIDER LIST GET");
		   
		   this.injectCommonAtrributesInHtmlPage(principal, model, request);
		   
		   model.addAttribute("providerList",this.hamaProviderService.findAll()
				   .stream()
				   .sorted(Comparator.comparing(HamaProvider::getProviderName))
				   .collect(Collectors.toList()));
		   
		   
		return "provider/providerList";
	}
	
	
    @Override
	@Secured({"MANAGER"})
	@GetMapping({"/provider/deleteGet/{id}"})
	public String providerDeleteGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		
		log.info("TRAZA PROVIDER DELETE GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("provider",this.hamaProviderService.findById(id).get());
		
		return "provider/providerDelete";
		
	}
	
    @Override
    @Secured({"MANAGER"})
	@GetMapping({"/provider/deleteConfirmed/{id}"})
	public String providerDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request){
    	
    	this.injectCommonAtrributesInHtmlPage(principal,model,request);
    	
    	
		
		log.info("TRAZA PROVIDER DELETE CONFIRMED");
		model.addAttribute("username", principal.getName());
		
		log.info("contact deleted= " + this.hamaProviderService.deleteById(id));
		
		return "redirect:/providerListGet";
		
	}
	
    @Override
	@GetMapping({"/provider/updateGet/{id}"})
	public String providerUpdateGet(@PathVariable("id") Long id,Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA PROVIDER UPDATE GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("provider", this.hamaProviderService.findById(id).get());
		
		return "provider/providerUpdate";
	}
	
    @Override
	@PostMapping({"/provider/updatePost"})
	public String providerUpdatePost(@Valid HamaProvider provider, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request){
		  
		log.info("TRAZA PROVIDER UPDATE POST");
		
		if(bindingResult.hasErrors()){
			log.error("El formulario de Provider tiener errores" + bindingResult.getAllErrors());
			
			return "redirect:/provider/updateGet/" + provider.getId();
			
		} else {
			
			log.info("Formulario correcto: " + provider);
			
			this.hamaProviderService.save(provider);
			
			return "redirect:/providerListGet";
			
		}
			
	}
	
    @Override
	@GetMapping({"/provider/addGet"})
	public String providerAddGet(Principal principal, Model model, HttpServletRequest request) {
		log.info("TRAZA PROVIDER ADD GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("provider", this.hamaProviderService.newProvider());
		
		return "provider/providerAdd";
		
	}
	
    @Override
	@PostMapping({"/provider/addPost"})
	public String providerAddPost(@Valid HamaProvider provider, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request){
		log.info("TRAZA PROVIDER ADD POST");
		
		if(bindingResult.hasErrors()) {
			
			log.error("El formulario de provider tiene errores" + bindingResult.getAllErrors());
			
			return "provider/providerAdd";
			
		}else {
			log.info("Formulario correcto: " + provider);
			
			this.hamaProviderService.save(provider);
			
			return "redirect:/providerListGet";
		}
		
	}
	
	
}
