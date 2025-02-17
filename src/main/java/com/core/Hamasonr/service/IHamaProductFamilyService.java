package com.core.Hamasonr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaProductFamily;

@Service
public interface IHamaProductFamilyService {
	
	List<HamaProductFamily> getAllFamilies();
	
	Optional<HamaProductFamily> getFamilyById(Long id);
	
	HamaProductFamily createFamily(HamaProductFamily family);
	
	Optional<HamaProductFamily> getFamilyByFamilyName(String familyName);
	

}
