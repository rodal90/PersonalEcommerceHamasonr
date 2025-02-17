package com.core.Hamasonr.controllerImpl;

import java.security.Principal;
import java.util.ArrayList;
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

import com.core.Hamasonr.controller.IHamaCustomerController;

import com.core.Hamasonr.data.model.HamaCustomer;

import com.core.Hamasonr.data.model.HamaUser;
import com.core.Hamasonr.data.repository.IHamaUserRepository;
import com.core.Hamasonr.service.IHamaCustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HamaCustomerControllerImpl extends HamaMasterControllerImpl implements IHamaCustomerController {

	
	@Autowired
	private IHamaCustomerService hamaCustomerService;
	
	
    @Override
	@GetMapping({"/customer/viewGet/{id}"})
	public String customerViewGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		
		System.out.println("TRAZA CUSTOMER VIEW GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("customer",this.hamaCustomerService.findById(id).get());
		
		return "customer/customerView";
	}
	
    @Override
	@GetMapping({"/customerListGet"})
	public String customerListGet(Principal principal, Model model, HttpServletRequest request) {
		   log.info("TRAZA CUSTOMER LIST GET");
		   
		   this.injectCommonAtrributesInHtmlPage(principal, model, request);
		   
		   model.addAttribute("customerList",this.hamaCustomerService.findAll()
				   .stream()
				   .sorted(Comparator.comparing(HamaCustomer::getFullname))
				   .collect(Collectors.toList()));
		   
		   
		return "customer/customerList";
	}
	
	
    @Override
	@Secured({"MANAGER"})
	@GetMapping({"/customer/deleteGet/{id}"})
	public String customerDeleteGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		
		log.info("TRAZA CUSTOMER DELETE GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("customer",this.hamaCustomerService.findById(id).get());
		
		return "customer/customerDelete";
		
	}
	
    @Override
    @Secured({"MANAGER"})
	@GetMapping({"/customer/deleteConfirmed/{id}"})
	public String customerDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request){
    	
    	this.injectCommonAtrributesInHtmlPage(principal,model,request);
    	
    	
		
		log.info("TRAZA CUSTOMER DELETE CONFIRMED");
		model.addAttribute("username", principal.getName());
		
		log.info("contact deleted= " + this.hamaCustomerService.deleteById(id));
		
		return "redirect:/customerListGet";
		
	}
	
    @Override
	@GetMapping({"/customer/updateGet/{id}"})
	public String customerUpdateGet(@PathVariable("id") Long id,Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA CUSTOMER UPDATE GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("customer", this.hamaCustomerService.findById(id).get());
		
		return "customer/customerUpdate";
	}
	
    @Override
	@PostMapping({"/customer/updatePost"})
	public String customerUpdatePost(@Valid HamaCustomer customer, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request){
		  
		log.info("TRAZA CUSTOMER UPDATE POST");
		
		if(bindingResult.hasErrors()){
			log.error("El formulario de Customer tiener errores" + bindingResult.getAllErrors());
			
			return "redirect:/customer/updateGet/" + customer.getId();
			
		} else {
			
			log.info("Formulario correcto: " + customer);
			
			this.hamaCustomerService.save(customer);
			
			return "redirect:/customerListGet";
			
		}
			
	}
	
    @Override
	@GetMapping({"/customer/addGet"})
	public String customerAddGet(Principal principal, Model model, HttpServletRequest request) {
		log.info("TRAZA CUSTOMER ADD GET");
		
		this.injectCommonAtrributesInHtmlPage(principal, model, request);
		
		model.addAttribute("customer", this.hamaCustomerService.newHamaCustomer());
		
		return "customer/customerAdd";
		
	}
	
  
	@Override
	@PostMapping({"/customer/addPost"})
	public String customerAddPost(@Valid HamaCustomer customer, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		
		 log.info("TRAZA CUSTOMER ADD POST");

	        // Verificar si hay errores de validación
	        if (bindingResult.hasErrors()) {
	            log.error("El formulario de customer tiene errores: " + bindingResult.getAllErrors());
	            return "customer/customerAdd"; // Retorna a la página de formulario si hay errores
	        } else {
	            log.info("Formulario correcto: " + customer);

	            // Asegurar que la lista de órdenes está vacía al inicio
	            if (customer.getHamaCustomerOrders() == null) {
	                customer.setHamaCustomerOrders(new ArrayList<>());
	            }

	            // Guardar el cliente en la base de datos
	            this.hamaCustomerService.save(customer);

	            // Redirigir a la lista de clientes después de guardar
	            return "redirect:/customerListGet";
	        }
			
	}

}
