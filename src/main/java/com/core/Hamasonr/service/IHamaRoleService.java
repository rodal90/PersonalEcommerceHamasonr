package com.core.Hamasonr.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaRole;


@Service
public interface IHamaRoleService {
	
public HamaRole save(HamaRole role);
	
	public Optional<HamaRole> findById(String rolename);
	
	public Set<HamaRole> findall();

}
