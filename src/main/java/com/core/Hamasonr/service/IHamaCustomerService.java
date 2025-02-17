package com.core.Hamasonr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaCustomer;
import com.core.Hamasonr.data.model.HamaUser;

import jakarta.transaction.Transactional;

@Service
public interface IHamaCustomerService {
	
	public HamaCustomer save(HamaCustomer customer);
	
	public Optional<HamaCustomer>findById(Long id);
	
	public List<HamaCustomer>findAll();
	
	public String deleteById(Long id);
	
	public HamaCustomer newHamaCustomer();
	
	
	

//	public String findByNameLike(String string);

}
