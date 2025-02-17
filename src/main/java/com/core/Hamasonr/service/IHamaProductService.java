package com.core.Hamasonr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.Hamasonr.data.model.HamaProduct;

@Service
public interface IHamaProductService {

	public List<HamaProduct> findAll();
	
	public Optional<HamaProduct>getProductById(Long id);
	
	public HamaProduct save(HamaProduct hamaProduct);
	
	public HamaProduct newHamaProduct();
	
	public String findByNameLike(String string);
	
	public String deleteById(Long id);
	
	public List<HamaProduct> findAllByOrderByName();

	

	public String storeImage(MultipartFile imageFile);

}
