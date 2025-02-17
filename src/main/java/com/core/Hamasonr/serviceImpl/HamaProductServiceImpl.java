package com.core.Hamasonr.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.Hamasonr.data.model.HamaProduct;
import com.core.Hamasonr.data.repository.IHamaProductRepository;
import com.core.Hamasonr.service.IHamaProductService;

@Service
public class HamaProductServiceImpl implements IHamaProductService {
	
	@Autowired

	private IHamaProductRepository hamaProductRepository;

	@Override
	public List<HamaProduct> findAll() {
		
		return hamaProductRepository.findAll();
	}

	@Override
	public Optional<HamaProduct> getProductById(Long id) {
		
		return this.hamaProductRepository.findById(id);
	}

	@Override
	public HamaProduct save(HamaProduct hamaProduct) {
		 
		return this.hamaProductRepository.save(hamaProduct);
	}

	@Override
	public HamaProduct newHamaProduct() {
		
		return new HamaProduct();
	}

	@Override
	public String findByNameLike(String string) {
	
		   List<HamaProduct> products = hamaProductRepository.findAll();
	        for (HamaProduct product : products) {
	            if (product.getName() != null && product.getName().
	            		toLowerCase().contains(string.toLowerCase())) {
	                return product.getName();
	            }
	        }
	        return "No se encontró un producto con nombre similar a: " + string;
	}

	
	@Override
	@Secured({"MANAGER"})
	public String deleteById(Long id) {
		this.hamaProductRepository.deleteById(id);	
		return this.hamaProductRepository.existsById(id)? "Borrado": "No Borrado";
	}

	@Override
	public List<HamaProduct> findAllByOrderByName() {
		return this.hamaProductRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(HamaProduct :: getName))
				.collect(Collectors.toList());
	}

	@Override
	public String storeImage(MultipartFile file) {
	    try {
	        // Define el directorio donde se almacenarán las imágenes (por ejemplo, "uploads/")
	        String uploadsDir = "uploads/";
	        File uploadDirFile = new File(uploadsDir);
	        if (!uploadDirFile.exists()) {
	            uploadDirFile.mkdirs();
	        }
	        
	        // Genera un nombre único para el archivo (puedes ajustar esta lógica según lo necesites)
	        String originalFilename = file.getOriginalFilename();
	        String fileName = System.currentTimeMillis() + "_" + originalFilename;
	        Path filePath = Paths.get(uploadsDir, fileName);
	        
	        // Copia el archivo al directorio de destino
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        
	        // Retorna la URL o ruta del archivo; por ejemplo, si configuras un ResourceHandler para /uploads/**
	        return "/uploads/" + fileName;
	    } catch (Exception e) {
	        throw new RuntimeException("Error saving file: " + e.getMessage());
	    }
	}

}
