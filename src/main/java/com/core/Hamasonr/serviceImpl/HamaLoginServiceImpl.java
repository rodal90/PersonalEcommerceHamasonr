package com.core.Hamasonr.serviceImpl;

import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaLogin;
import com.core.Hamasonr.service.IHamaLoginService;

@Service
public class HamaLoginServiceImpl implements IHamaLoginService {

	@Override
	public HamaLogin newEntity() {
		
		return new HamaLogin();
	}

}
