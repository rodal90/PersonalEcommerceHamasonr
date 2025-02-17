package com.core.Hamasonr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaProductRate;

@Service
public interface IHamaProductRateService {
	
	public HamaProductRate save(HamaProductRate rate );
	
	public Optional<HamaProductRate> findById(Long id);
	
	public List<HamaProductRate>findall();
	

}
