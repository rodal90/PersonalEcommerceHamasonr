package com.core.Hamasonr.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import com.core.Hamasonr.data.model.HamaUser;
import com.core.Hamasonr.data.repository.IHamaUserRepository;
import com.core.Hamasonr.service.IHamaUserService;


@Service
public class HamaUserServiceImpl implements IHamaUserService {
	
	@Autowired
	private IHamaUserRepository hamaUserRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<HamaUser> userOpt = this.findById(username);
		if(userOpt.isEmpty()) {
			//ya esta creada en la libreria de excepciones de spring por eso la podemos usar
			throw new UsernameNotFoundException(username);
		}
		//primero quiero comprobar que no este vacio, y luego si se que no esta vacio ya saco lo que tenga adentro.
		return userOpt.get();
	}

	@Override
	public HamaUser save(HamaUser user) {
		
		return this.hamaUserRepository.save(user);
	}

	@Override
	public Optional<HamaUser> findById(String username) {
		
		return this.hamaUserRepository.findById(username);
	}

	@Override
	public Set<HamaUser> findall() {
		
		return new HashSet<HamaUser>(this.hamaUserRepository.findAll());
	}


	@Override
	public HamaUser newUser() {
		  HamaUser user = new HamaUser();
		  return user;
	}

	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
