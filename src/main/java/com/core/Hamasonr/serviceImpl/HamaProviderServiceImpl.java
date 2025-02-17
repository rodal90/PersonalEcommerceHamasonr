package com.core.Hamasonr.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import com.core.Hamasonr.data.model.HamaProvider;
import com.core.Hamasonr.data.repository.IHamaProviderRepository;
import com.core.Hamasonr.service.IHamaProviderService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HamaProviderServiceImpl implements IHamaProviderService {
	
	@Autowired
	private IHamaProviderRepository hamaProviderRepository;
	
	@Override
	public HamaProvider save(HamaProvider provider ) {
		log.warn("TRAZA: entrando en ProviderServiceImpl ->save");
		return this.hamaProviderRepository.save(provider);
		
	}

	@Override
	public Optional<HamaProvider> findById(Long id) {
		
		return this.hamaProviderRepository.findById(id);
	}

	@Override
	@Secured("MANAGER")
	public String deleteById(Long id) {
		
		this.hamaProviderRepository.deleteById(id);		
		return this.hamaProviderRepository.existsById(id)? "Borrado" : "No Borrado";
	}

	@Override
	public List<HamaProvider> findAll() {
	
		return this.hamaProviderRepository.findAll();
	}

	@Override
	public String findByNameLike(String string) {
	
		return null;
	}

	@Override
	public List<HamaProvider> findAllByOrderByName() {
		
		return this.hamaProviderRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(HamaProvider::getProviderName))
			    .collect(Collectors.toList());
	}

	@Override
	public HamaProvider newProvider() {
		
		return new HamaProvider();
	}
	
	

}
