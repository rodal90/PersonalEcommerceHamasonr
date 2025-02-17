package com.core.Hamasonr.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaProductFamily;
import com.core.Hamasonr.data.repository.IHamaProductFamilyRepository;
import com.core.Hamasonr.service.IHamaProductFamilyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HamaProductFamilyServiceImpl implements IHamaProductFamilyService {

	@Autowired
	private IHamaProductFamilyRepository familyRepository;
	
	@Override
	public List<HamaProductFamily> getAllFamilies() {
		
		return familyRepository.findAll();
	}

	@Override
	public Optional<HamaProductFamily> getFamilyById(Long id) {
		
		return familyRepository.findById(id);
	}

	@Override
	public HamaProductFamily createFamily(HamaProductFamily family) {
		log.info("Creando familia de productos:{}",family);
		
		return familyRepository.save(family);
	}
	
	 @Override
	    public Optional<HamaProductFamily> getFamilyByFamilyName(String familyName) {
	        return familyRepository.findByFamilyName(familyName);
	    }
	

}
