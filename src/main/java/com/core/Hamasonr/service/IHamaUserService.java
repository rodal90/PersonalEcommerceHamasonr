package com.core.Hamasonr.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaUser;
import java.util.Optional;
import java.util.Set;

@Service
public interface IHamaUserService extends UserDetailsService {
	
	public HamaUser save(HamaUser user);
	
	public Optional<HamaUser> findById(String username);
	
	public Set<HamaUser> findall();

	public HamaUser newUser();

	public String deleteById(String id);


	

}
