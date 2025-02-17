package com.core.Hamasonr.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaRole;
import com.core.Hamasonr.data.repository.IHamaRoleRepository;
import com.core.Hamasonr.service.IHamaRoleService;


@Service
public class HamaRoleServiceImpl implements IHamaRoleService {
	
	@Autowired
	private IHamaRoleRepository HamaRoleRepository;

	@Override
	public HamaRole save(HamaRole role) {
		
		return this.HamaRoleRepository.save(role);
	}

	@Override
	public Optional<HamaRole> findById(String rolename) {
		
		return this.HamaRoleRepository.findById(rolename);
	}

	@Override
	public Set<HamaRole> findall() {
		
		return new HashSet<HamaRole>(this.HamaRoleRepository.findAll());
	}

}
