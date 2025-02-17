package com.core.Hamasonr.controllerImpl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.core.Hamasonr.controller.IHamaMasterController;
import com.core.Hamasonr.data.model.HamaProduct;
import com.core.Hamasonr.data.model.HamaProductFamily;
import com.core.Hamasonr.data.model.HamaProductRate;
import com.core.Hamasonr.data.model.HamaProvider;
import com.core.Hamasonr.service.IHamaProductFamilyService;
import com.core.Hamasonr.service.IHamaProductRateService;
import com.core.Hamasonr.service.IHamaProductService;
import com.core.Hamasonr.service.IHamaProviderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HamaProductControllerImpl extends HamaMasterControllerImpl implements IHamaMasterController  {
	
	@Autowired
	private IHamaProductService hamaProductService;
	
	@Autowired
	private IHamaProductFamilyService hamaProductFamilyService;
	
	@Autowired
	private IHamaProductRateService hamaProductRateService;
	
	@Autowired
	private IHamaProviderService hamaProviderService;
	
	// Listar todos los productos
    @GetMapping("/product/list")
    public String productListGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando lista de productos");
        model.addAttribute("productList", hamaProductService.findAll());
        return "product/productList";
    }

    // Ver detalles de un producto
    @GetMapping("/product/view/{id}")
    public String productViewGet(@PathVariable("id") Long id, Model model) {
        log.info("Viendo producto con ID: " + id);
        model.addAttribute("product", hamaProductService.getProductById(id).orElse(null));
        return "product/productView";
    }

    // Mostrar formulario de nuevo producto
    @GetMapping("/product/add")
    public String productAddGet(Model model) {
        log.info("Cargando formulario para nuevo producto");
        model.addAttribute("product", hamaProductService.newHamaProduct());
        
     // Agregar lista de familias al modelo
        List<HamaProductFamily> families = hamaProductFamilyService.getAllFamilies();
        model.addAttribute("families", families);
        
        // Agregar lista de proveedores al modelo
        List<HamaProvider> providers = hamaProviderService.findAll();
        model.addAttribute("providers", providers);
        
        // Agregar lista de rates al modelo
        List<HamaProductRate> rates = hamaProductRateService.findall();
        model.addAttribute("rates", rates);
        
        
        
        return "product/productAdd";
    }

    // Guardar nuevo producto
    @PostMapping("/product/add")
    public String productAddPost(@Valid HamaProduct product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("Errores en el formulario: " + bindingResult.getAllErrors());
            return "product/productAdd";
        }
        hamaProductService.save(product);
        log.info("Producto agregado: " + product);
        return "redirect:/product/list";
    }

    // Mostrar formulario de edición
    @GetMapping("/product/edit/{id}")
    public String productEditGet(@PathVariable("id") Long id, Model model) {
        log.info("Cargando producto para edición: " + id);
        model.addAttribute("product", hamaProductService.getProductById(id).orElse(null));
        return "product/productUpdate";
    }

    @PostMapping("/product/edit")
    public String productEditPost(@Valid @ModelAttribute("product") HamaProduct product, 
                                   BindingResult bindingResult,
                                   @RequestParam("imageFile") MultipartFile imageFile,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            log.error("Errores en el formulario de edición: " + bindingResult.getAllErrors());
            return "product/productUpdate"; // Vuelve al formulario si hay errores
        }
        
        // Si se sube un nuevo archivo, procesa la imagen
        if (!imageFile.isEmpty()) {
            try {
                // Llama al service para almacenar la imagen y obtener la URL resultante
                String imageUrl = hamaProductService.storeImage(imageFile);
                product.setImageUrl(imageUrl);
            } catch (Exception e) {
                log.error("Error subiendo la imagen", e);
                model.addAttribute("errorMessage", "Error al subir la imagen. Inténtalo de nuevo.");
                return "product/productUpdate"; // Vuelve al formulario si hay un error
            }
        }
        
        hamaProductService.save(product);
        log.info("Producto actualizado: " + product);
        return "redirect:/product/list"; // Redirige a la lista de productos después de guardar
    }


    // Eliminar un producto
    @Secured({"MANAGER"})
    @GetMapping("/product/delete/{id}")
    public String productDelete(@PathVariable("id") Long id) {
        log.info("Eliminando producto con ID: " + id);
        hamaProductService.deleteById(id);
        return "redirect:/product/list";
    }

    // Buscar productos por nombre
    @GetMapping("/product/search")
    public String searchProducts(@RequestParam("name") String name, Model model) {
        log.info("Buscando productos que coincidan con: " + name);
        model.addAttribute("productList", hamaProductService.findAllByOrderByName().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList());
        return "product/productList";
    }
    
    //AHORA LOS QUE NECESITO PARA LISTAR EDITAR ETC. 

}
