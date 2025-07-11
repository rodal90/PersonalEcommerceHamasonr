package com.core.Hamasonr.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.Hamasonr.config.LanguageResourceBundleMessage;
import com.core.Hamasonr.controller.IStartController;
import com.core.Hamasonr.data.model.HamaLogin;
import com.core.Hamasonr.service.IHamaLoginService;
import com.core.Hamasonr.service.IHamaProductService;
import com.core.Hamasonr.data.model.HamaEncryption;
import com.core.Hamasonr.data.model.HamaLogin;
import com.core.Hamasonr.service.IHamaEncryptionService;
import com.core.Hamasonr.service.IHamaLoginService;

/*import com.core.timmy.serviceImpl.PropertiesForJasyptServiceImpl;*/

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//sirve para no tener que utilizar en los nombres de paquetes y de clases muy largos no necesitemos usar todo el nombre
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@Controller
@Slf4j
public class StartControllerImpl implements IStartController {
	
	//auto inyecto el servicio del login para poder usarlo abajo
	@Autowired
	private IHamaLoginService loginService;
	
	@Autowired
	private IHamaEncryptionService encryptionService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
		
/*	@Autowired*/
/*	private PropertiesForJasyptServiceImpl propertiesForJasyptServiceImpl;*/
	
	@Autowired
	private LanguageResourceBundleMessage languageResourceBundleMessage;
	
	@Autowired
	private IHamaProductService productService;
	
	
	
	
	
@GetMapping({"/logoutGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	
	public String logoutGet(
			//si el usuario está auntenticada principal tiene el username y los roles.
			Principal principal,
			//model para inyectar datos a la maquina
			Model model,
			//clase de Java que tiene la cabecera de la petición de request de HTML
			HttpServletRequest request) {
		
		System.out.println("TRAZA logoutGet");
		
		//Invalidate session. No importa si es una session anónima o de usuario
		request.getSession().invalidate();
		//
		// Inject data into html pag
		model.addAttribute("login",loginService.newEntity());
		
		//return "redirect:/loginGet?logoutOk";
		
		return "loginPage";
		
	}
	
	

	@Override
	@GetMapping({"/loginGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	
	public String loginGet(Model model) {
		
		System.out.println("TRAZA loginGet");
		
		//el objeto que le inyectamos puede ir con datos o sin datos. Como añadimos un objeto. Necesitamos indexar todo lo que necesita antes
		//de retornar a la loginPage. 
		//necesitamos darle un nombre de atributo y decirle también
		//debido a que no se recomienda un controllador para 
		//login dentro de html Inject data into html page
		model.addAttribute("login", loginService.newEntity());
		
		return "loginPage";
	}
	
	
	@Override
	@GetMapping({"/homeGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String homeGet(Principal principal, Model model, HttpServletRequest request) {
		
		System.out.println("TRAZA homeGet");
		model.addAttribute("productList", productService.findAll());
		
		//el objeto que le inyectamos puede ir con datos o sin datos. Como añadimos un objeto. Necesitamos indexar todo lo que necesita antes
		//de retornar a la loginPage. 
		//necesitamos darle un nombre de atributo y decirle también
		//debido a que no se recomienda un controllador para 
		//login dentro de html Inject data into html page
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);
		
		//Testing NifValidator el metodo(funcion):
		/*NifValidator.nifIsCorrectAndNotNull("12345678A");
		NifValidator.nifIsCorrectAndNotNull("12345678Z"); //la Z nos da correcto porque el calculo de los números nos da Z usando el listado
		NifValidator.nifIsCorrectAndNotNull("12345678z");
		NifValidator.nifIsCorrectAndNotNull("abc45678");
		NifValidator.nifIsCorrectAndNotNull(null);*/
		
		//Check Max key size for encryption:
		/*try {
			int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES"); //Other aalgorithm
			log.info("Max key size for AES algorithm= " + maxKeySize);
			
		} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
			log.error("EXCEPTION when calculating MAx key size for AES algorithm= "+ e.getMessage());
			// TODO: handle exception
		}
		*/
		//un obejto vacio de encryption para 
		/*encryptionService.findAllEncodersName();
		Encryption encryption= encryptionService.newEntity();
		encryption.setTextToEncrypt("En un lugar de la Mancha");
		encryption.setPasswordEncoderString("Pbkdf2");
		log.info("El texto encriptado es: "+ encryptionService.encryptText(encryption).getTextEncrypted());*/
		
		// Test JASYPT encryption for properties:
			/*	log.info("admin.name (environment)= " + propertiesForJasyptServiceImpl.getPropertyUsingEnvironment("admin.name"));
				log.info("admin.email(value)= " + propertiesForJasyptServiceImpl.getAdminEmail());
				log.info("admin.email(environment)= " + propertiesForJasyptServiceImpl.getPropertyUsingEnvironment("admin.email"));		
				propertiesForJasyptServiceImpl.getEncryptedPropertiesMap();
				log.info("spring.datasource.password for H2= " + propertiesForJasyptServiceImpl.getPropertyUsingEnvironment("spring.datasource.password"));		
				//*/
			
		return "masterfull";
	}

	@Override
	@PostMapping({"/loginPost"})
	public String loginPost(
			//el Valid Le dice a spring que realice la validaciòn de los campos que traemos del objeto login, nos dice que error se a produce
			// osea comprueba los parametros que pusimos como los limites de caracteres y las expresiones regulares
			@Valid HamaLogin login,
			//lo guarda en el bindiResult y nos dice en que campo aparece error.
			BindingResult bindingResult,
			HttpServletRequest request,
			Model model) {
		System.out.println("TRAZA loginPost");
		//nos muestra si ha habido errores, 
		if(bindingResult.hasErrors()) {
			//con get all errors iría uno por uno por todos los campos y nos lo mostraria como warning.
			log.warn("VALIDATION ERRORS!!: "+ bindingResult.getAllErrors());
			//cuando tenga error lo enviaremos de vuelta a la loginPage
			return "loginPage";
			
			
		}
		else {
			//esta es una clase ya hecha de spring que tiene que ver con seguridad por eso solo se importa
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(login.getHamaUsername(),login.getHamaPassword());
			//esta también. Es para autenticar el Token
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
				
				if (authentication.isAuthenticated()) {
					
					//nuevo objeto de la clase SecurityContext
					
					SecurityContext securityContext = SecurityContextHolder.getContext();
					
					//el contexto de la aplicación es importante la session se asocia a cada uno de los contexto en este caso es timmy
					
					securityContext.setAuthentication(authentication);
					
					HttpSession session = request.getSession(true);
					session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
					
					
                 } else {
					
					log.info("BAD CREDENTIALS! NOT LOGGED");
					
					return "loginPage";

				}
			
		}
		
		return "redirect:/homeGet";
	}
	
	@Override
	public void injectCommonAtrributesInHtmlPage(
			
		
					//todas estas son inyecciones para poder usarlas en distintas partes del código
			
			Principal principal,
			Model model,
			HttpServletRequest request) {
		
		model.addAttribute("username",request.getUserPrincipal().getName()); //si ha recogido al usuario autenticado el getUserPrincipal por eso se debe usar aqui en vez del principal
		model.addAttribute("userPicture", "");
		
		model.addAttribute("languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		model.addAttribute("requestURI", request.getRequestURI());
		
		log.warn("TRAZA: "+"languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		
		log.warn("Traza2: "+"requestURI", request.getRequestURI());
	
	}



	@Override
	public String dumpDBGet() {
		// TODO Auto-generated method stub
		return null;
	}	

}
