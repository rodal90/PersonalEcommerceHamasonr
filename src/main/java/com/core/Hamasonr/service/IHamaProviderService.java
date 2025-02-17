package com.core.Hamasonr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaProvider;


@Service
public interface IHamaProviderService {

	public HamaProvider save(HamaProvider Provider);

	public Optional<HamaProvider> findById(Long id);

	public String deleteById(Long id);

	public List<HamaProvider> findAll();

	public String findByNameLike(String string);

	public List<HamaProvider> findAllByOrderByName();

	public HamaProvider newProvider();

}
