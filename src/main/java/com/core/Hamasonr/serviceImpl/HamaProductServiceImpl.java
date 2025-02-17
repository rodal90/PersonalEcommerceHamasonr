package com.core.Hamasonr.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	 // Define la ruta donde deseas guardar las imágenes
    @Value("${image.upload.dir}")
    private String uploadDir;

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
    public String storeImage(MultipartFile imageFile) {
        // Verifica que el archivo no esté vacío
        if (imageFile.isEmpty()) {
            throw new RuntimeException("No se puede almacenar un archivo vacío.");
        }

        try {
            // Obtiene el nombre original del archivo
            String fileName = imageFile.getOriginalFilename();
            // Define la ruta completa donde se guardará la imagen
            Path path = Paths.get(uploadDir + fileName);
            // Crea el directorio si no existe
            Files.createDirectories(path.getParent());
            // Copia el archivo a la ubicación deseada
            Files.copy(imageFile.getInputStream(), path);
            // Devuelve la ruta o URL donde se ha almacenado la imagen
            return path.toString(); // O puedes devolver una URL relativa
        } catch (IOException e) {
            throw new RuntimeException("Error al almacenar la imagen: " + e.getMessage());
        }
    }
}


