package com.core.Hamasonr.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaProductRate;
import com.core.Hamasonr.data.repository.IHamaProductRateRepository;
import com.core.Hamasonr.service.IHamaProductRateService;

@Service
public class HamaProductRateServiceImpl implements IHamaProductRateService {
	
	@Autowired
	private IHamaProductRateRepository  hamaProductRateRepository;

	@Override
	public HamaProductRate save(HamaProductRate rate) {
		
		return hamaProductRateRepository.save(rate);
	}

	@Override
	public Optional<HamaProductRate> findById(Long id) {
		return this.hamaProductRateRepository.findById(id);
		
	}

	@Override
	public List<HamaProductRate> findall() {
		
		return this.hamaProductRateRepository.findAll();
	}

}
