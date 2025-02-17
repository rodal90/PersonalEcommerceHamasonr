package com.core.Hamasonr;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.core.Hamasonr.data.model.HamaCustomer;
import com.core.Hamasonr.data.model.HamaCustomerOrder;
import com.core.Hamasonr.data.model.HamaOrderLine;
import com.core.Hamasonr.data.model.HamaProduct;
import com.core.Hamasonr.data.model.HamaProductFamily;
import com.core.Hamasonr.data.model.HamaProductRate;
import com.core.Hamasonr.data.model.HamaProvider;
import com.core.Hamasonr.data.model.HamaRole;
import com.core.Hamasonr.data.model.HamaUser;
import com.core.Hamasonr.data.repository.IHamaCustomerOrderRepository;
import com.core.Hamasonr.data.repository.IHamaCustomerRepository;
import com.core.Hamasonr.data.repository.IHamaOrderLineRepository;
import com.core.Hamasonr.data.repository.IHamaProductFamilyRepository;
import com.core.Hamasonr.data.repository.IHamaProductRateRepository;
import com.core.Hamasonr.data.repository.IHamaProductRepository;
import com.core.Hamasonr.data.repository.IHamaProviderRepository;
import com.core.Hamasonr.data.repository.IHamaRoleRepository;
import com.core.Hamasonr.data.repository.IHamaUserRepository;


@SpringBootApplication
public class HamasonrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamasonrApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner( // es como que si estuvieramos usando la consola

			IHamaRoleRepository hamaRoleRepository,IHamaUserRepository hamaUserRepository, IHamaProductRepository hamaProductRepository,
			IHamaProductFamilyRepository hamaProductFamilyRepository, IHamaProductRateRepository hamaProductRateRepository, 
			IHamaProviderRepository hamaProviderRepository, IHamaCustomerRepository hamaCustomerRepository, 
			IHamaOrderLineRepository hamaOrderLineRepository , IHamaCustomerOrderRepository hamaCustomerOrderRepository

	) {

		return args -> { 
			
			//ROLES
			hamaRoleRepository.save(new HamaRole("ADMIN"));
			hamaRoleRepository.save(new HamaRole("USER"));
			hamaRoleRepository.save(new HamaRole("MANAGER"));
			
			//USERS "corazon"
			hamaUserRepository.save(new HamaUser("wendy","wendy34@gmail.com","+34 658 149543", "Wendy Gabriela Alccantara",
					"Pascual Toso 1545 ave.Reyes","$2a$12$EO2i7fp6MKA.yKVxU0CT6umLu1qAnEquEv/VI8DAQvgguet6CXVTS", LocalDate.now().plusMonths(1),
					LocalDate.now().plusMonths(1),true,false,
					Set.of(hamaRoleRepository.findById("USER").get())));
			//"coral"
			hamaUserRepository.save(new HamaUser("rodri","rodtota@gmail.com","+34 658 949909", "Rodrigo Alessandro Meneses",
					"30 av. 17-80 z.12 Condominio Pamplona","$2a$12$9T/qoygT2uTedUeX8YSblefLZDv8cqL5WQh4Z/Em39HZe37Tz/UL6", LocalDate.now().plusMonths(1),
					LocalDate.now().plusMonths(1),true,false,
					Set.of(hamaRoleRepository.findById("USER").get(), hamaRoleRepository.findById("ADMIN").get(), hamaRoleRepository.findById("MANAGER").get())));
			//"gori"
			hamaUserRepository.save(new HamaUser("gorilla","gorilona@gmail.com","+34 687 955989", "Gorila Fuellez Cantera",
					"Vitralon","$2a$12$zQOlnXGLtKNeXqTnoNfkpedkxRlia7NHbgbm/Q75/IwNpeCZK3ooa", LocalDate.now().plusMonths(1),
					LocalDate.now().plusMonths(1),true,false,
					Set.of(hamaRoleRepository.findById("USER").get(), hamaRoleRepository.findById("ADMIN").get())));
			//"olor"
			hamaUserRepository.save(new HamaUser("carlitos", "carloseduardo@gmail.com", "+34 679 125647", "Carlos Eduardo Silva",
				    "Avenida de los Reyes 25, Madrid", "$2b$12$Fsj2Fh33CBSI4lWHrGpMtuFRD7SmV9pjL6sjOjtYNFH4bxZgn6ClO", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
			//"queso"
				hamaUserRepository.save(new HamaUser("laura", "laura.martin@gmail.com", "+34 666 987654", "Laura Martín López",
				    "Calle Mayor 14, Madrid", "$2b$12$y3tAGNSCVoWH.pBKcY1VHumt1hOaBherHKb0q91MSONy5b5OUUV5O", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
		   //"ravio"
				hamaUserRepository.save(new HamaUser("alfredo", "alfredo.jimenez@gmail.com", "+34 634 456123", "Alfredo Jiménez Pérez",
				    "Calle Gran Vía 78, Madrid", "$2b$12$qL33WWXdvU1D/m07E9zE7e3avz3kLwimqRdfA68CxBd9AKCzH01pm", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
		  //"pavimento"
				hamaUserRepository.save(new HamaUser("isabel", "isabel.garcia@gmail.com", "+34 620 345678", "Isabel García Sánchez",
				    "Calle de la Paz 3, Madrid", "$2b$12$PHB.S2QDFudsNHCYKVoKfu7KBDZ3JD2KeIbkQ6kCRYPgTVTMOXY2O", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
		  //"freno"
				hamaUserRepository.save(new HamaUser("miguel", "miguel.gomez@gmail.com", "+34 612 876543", "Miguel Ángel Gómez",
				    "Calle del Prado 21, Madrid", "$2b$12$9FEzUqaEkZAqjuHNmusUd.CqiYSVXweNL9Di47cIf5bZ8puJrJ.ya", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
		//"azul"
				hamaUserRepository.save(new HamaUser("ana", "ana.lopez@gmail.com", "+34 699 654321", "Ana María López",
				    "Calle del Lago 19, Madrid", "$2b$12$sH7jBB86DrhaSwB9K5QZXO5V3Yg6d0c1ohtu/kekgEw5YCefH.8OW", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
	    //"compa"
				hamaUserRepository.save(new HamaUser("jose", "joseluis.martin@gmail.com", "+34 690 123987", "José Luis Martín",
				    "Calle de la Sierra 8, Madrid", "$2b$12$dohjOPrggWcsuMSN5qdcL.Clvh2rfmexgC8xs592gk.GdwwxCtpeW", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
				    Set.of(hamaRoleRepository.findById("USER").get())));
	
			//PRODUCT RATES
			
			hamaProductRateRepository.save(new HamaProductRate(null, "Bueno"));
			hamaProductRateRepository.save(new HamaProductRate(null, "Muy Bueno"));
			hamaProductRateRepository.save(new HamaProductRate(null, "Malo"));
			hamaProductRateRepository.save(new HamaProductRate(null, "Muy Malo"));
			
			//PRODUCT FAMILY
			hamaProductFamilyRepository.save(new HamaProductFamily(null, "ALIMENTOS"));
			hamaProductFamilyRepository.save(new HamaProductFamily(null, "TECNOLOGIA"));
			hamaProductFamilyRepository.save(new HamaProductFamily(null, "PERFUMERIA"));
			hamaProductFamilyRepository.save(new HamaProductFamily(null, "JOYERIA"));
			hamaProductFamilyRepository.save(new HamaProductFamily(null, "INDUMENTARIA"));

			
			// Guardamos 15 providers en una sola línea cada uno:
			hamaProviderRepository.save(new HamaProvider(null, "Alimentos del Norte S.A.", "AN1234567", "912345678", "contacto@alimentosnorte.com", "Calle Norte 45 Madrid", new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Tech Innovations S.L.", "T2345678I", "913456789", "info@techinnovations.com", "Avenida Tecnología 10 Barcelona", new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Fragancias Exquisitas", "P3456789F", "914567890", "ventas@fraganciasexquisitas.com", "Calle Perfume  22 Sevilla", new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Joyas & Encanto", "J4567890E", "915678901", "contacto@joyasencanto.com", "Plaza de Joyas 3 Valencia", new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Moda Urbana", "I5678901U", "916789012", "info@modaurbana.com", "Calle Fashion 15 Bilbao", new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Delicias Gourmet", "A6789012G", "917890123", "ventas@deliciasgourmet.com", "Avenida Gourmet 8 Zaragoza", new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Soluciones Tecnológicas", "T7890123S", "918901234", "soporte@solucionestec.com", "Calle Digital 30 Málaga", new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Estilo y Sabor", "E8901234S", "919012345", "contacto@estiloysabor.com", "Calle Combinada 5 Murcia",new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Brillo y Estilo", "B9012345B", "920123456", "ventas@brilloyestilo.com", "Avenida del Lujo 7 Palma",new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Sabores del Sur", "A0123456S", "921234567", "info@saboresdelsur.com", "Calle del Sur 12 Granada",new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Innovatech", "T1234567I", "922345678", "contacto@innovatech.com", "Paseo Tecnológico 20 Sevilla",new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Aromas del Mundo", "P2345678A", "923456789", "info@aromasdelmundo.com", "Calle de Aromas 9 Valencia",new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Elegancia Joyería", "J3456789E", "924567890", "ventas@eleganciajoyeria.com", "Avenida de Brillo 11 Madrid",new ArrayList<>()));
			hamaProviderRepository.save(new HamaProvider(null, "Trendsetters Clothing", "I4567890T", "925678901", "contacto@trendsetters.com", "Calle de la Moda 6 Barcelona",new ArrayList<>() ));
			hamaProviderRepository.save(new HamaProvider(null, "Fusion Market", "F5678901F", "926789012", "info@fusionmarket.com", "Avenida Fusión 14 Bilbao", new ArrayList<>()));			
			
			
			// POR SI ACASO 
			
			/*
			hamaProductRepository.save(new HamaProduct(null, "Alubias Blancas", "Deliciosas alubias blancas para guisos.", "http://localhost/src/main/resources/static/img/alubias-blancas.jpg", new BigDecimal("4.99"), 150, new BigDecimal("10.18"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Espaghetti", "Pasta de trigo de excelente calidad.", "http://localhost/src/main/resources/static/img/espaghetti.jpg", new BigDecimal("2.99"), 100, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(4L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Frijoles", "Frijoles rojos ideales para cocinar.", "http://localhost/src/main/resources/static/img/frijoles.jpg", new BigDecimal("3.50"), 120, new BigDecimal("15.40"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(3L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Harina", "Harina de trigo para repostería.", "http://localhost/src/main/resources/static/img/harina.jpg", new BigDecimal("1.99"), 200, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Queso", "Queso curado de gran calidad.", "http://localhost/src/main/resources/static/img/queso.jpg", new BigDecimal("7.99"), 80, new BigDecimal("20.00"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(2L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Salsa Andaluza", "Salsa especial para acompañar carnes.", "http://localhost/src/main/resources/static/img/salsa-andaluza.png", new BigDecimal("5.99"), 90, new BigDecimal("10.23"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(3L).get(), null));

			hamaProductRepository.save(new HamaProduct(null, "PS5", "Consola de última generación.", "http://localhost/src/main/resources/static/img/ps5.jpg", new BigDecimal("499.99"), 50, new BigDecimal("15.00"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), null, null));
			hamaProductRepository.save(new HamaProduct(null, "Switch", "Consola portátil para disfrutar en cualquier lado.", "http://localhost/src/main/resources/static/img/switch.jpg", new BigDecimal("299.99"), 70, new BigDecimal("10.11"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(2L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "iPhone", "Última versión del smartphone de Apple.", "http://localhost/src/main/resources/static/img/iphone.jpg", new BigDecimal("999.99"), 40, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Laptop", "Ordenador portátil de alto rendimiento.", "http://localhost/src/main/resources/static/img/laptop.jpg", new BigDecimal("799.99"), 60, new BigDecimal("10.25"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "TV", "Televisor 4K UHD con HDR.", "http://localhost/src/main/resources/static/img/tv.jpg", new BigDecimal("599.99"), 90, new BigDecimal("10.15"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), null, null));
			hamaProductRepository.save(new HamaProduct(null, "Android", "Smartphone con sistema operativo Android.", "http://localhost/src/main/resources/static/img/android.jpg", new BigDecimal("399.99"), 100, new BigDecimal("15.30"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(2L).get(), null));

			hamaProductRepository.save(new HamaProduct(null, "Chanel", "Perfume de lujo para ocasiones especiales.", "http://localhost/src/main/resources/static/img/chanel.jpg", new BigDecimal("89.99"), 80, new BigDecimal("10.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(3L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Boss", "Aroma sofisticado y elegante.", "http://localhost/src/main/resources/static/img/boss.jpg", new BigDecimal("79.99"), 100, new BigDecimal("15.25"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(4L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Bronco", "Fragancia masculina con carácter.", "http://localhost/src/main/resources/static/img/bronco.jpg", new BigDecimal("59.99"), 120, new BigDecimal("10.25"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Pagan", "Perfume místico y envolvente.", "http://localhost/src/main/resources/static/img/pagan.jpg", new BigDecimal("69.99"), 150, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(2L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Opium", "Fragancia exótica y misteriosa.", "http://localhost/src/main/resources/static/img/opium.jpg", new BigDecimal("99.99"), 60, new BigDecimal("11.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(3L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Rosa", "Aroma floral y delicado.", "http://localhost/src/main/resources/static/img/rosa.jpg", new BigDecimal("49.99"), 110, new BigDecimal("20.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(1L).get(), null));

			hamaProductRepository.save(new HamaProduct(null, "Reloj", "Elegante reloj de acero inoxidable.", "http://localhost/src/main/resources/static/img/reloj.jpg", new BigDecimal("199.99"), 50, new BigDecimal("10.50"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Pulsera2", "Pulsera de plata con diseño minimalista.", "http://localhost/src/main/resources/static/img/pulsera2.jpg", new BigDecimal("79.99"), 90, new BigDecimal("15.00"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Pulsera", "Brazalete de oro con incrustaciones.", "http://localhost/src/main/resources/static/img/pulsera.jpg", new BigDecimal("249.99"), 30, new BigDecimal("10.40"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(4L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Collar", "Collar de diamantes finamente elaborado.", "http://localhost/src/main/resources/static/img/collar.jpg", new BigDecimal("299.99"), 20, new BigDecimal("15.00"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Aretes", "Aretes de oro con piedras preciosas.", "http://localhost/src/main/resources/static/img/aretes.jpg", new BigDecimal("99.99"), 50, new BigDecimal("10.8"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(2L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Aretes1", "Aretes de plata con diseño elegante.", "http://localhost/src/main/resources/static/img/aretes1.jpg", new BigDecimal("89.99"), 60, new BigDecimal("10.11"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), null));

			hamaProductRepository.save(new HamaProduct(null, "Camisa", "Camisa de algodón para hombre.", "http://localhost/src/main/resources/static/img/camisa.jpg", new BigDecimal("29.90"), 100, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(2L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Pantalón", "Pantalón de mezclilla de corte clásico.", "http://localhost/src/main/resources/static/img/pantalon.jpg", new BigDecimal("39.90"), 150, new BigDecimal("10.60"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(4L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Zapatos", "Zapatos de cuero negro de alta calidad.", "http://localhost/src/main/resources/static/img/zapatos.jpg", new BigDecimal("59.90"), 200, new BigDecimal("10.80"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Bermuda", "Bermuda para hombre de estilo moderno.", "http://localhost/src/main/resources/static/img/bermuda.jpg", new BigDecimal("19.90"), 180, new BigDecimal("10.90"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(2L).get(), null));
			hamaProductRepository.save(new HamaProduct(null, "Sombrero", "Sombrero de paja para el verano.", "http://localhost/src/main/resources/static/img/sombrero.jpg", new BigDecimal("14.90"), 300, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(1L).get(), null));
			*/

			hamaProductRepository.save(new HamaProduct(null, "Alubias Blancas", "Deliciosas alubias blancas para guisos.", "alubias-blancas.jpg", new BigDecimal("4.99"), 150, new BigDecimal("10.18"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(6L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Espaghetti Coraline", "Pasta de trigo de excelente calidad.", "espaghetti.jpg", new BigDecimal("2.99"), 100, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(4L).get(), hamaProviderRepository.findById(8L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Frijoles el Mejor", "Frijoles rojos ideales para cocinar.", "frijoles.jpg", new BigDecimal("3.50"), 120, new BigDecimal("15.40"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(3L).get(), hamaProviderRepository.findById(10L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Harina de Mandragora", "Harina de trigo para repostería.", "harina.jpg", new BigDecimal("1.99"), 200, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(1L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Queso Roquefeller", "Queso curado de gran calidad.", "queso.jpg", new BigDecimal("7.99"), 80, new BigDecimal("20.00"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(6L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Salsa Andaluza", "Salsa especial para acompañar carnes.", "salsa-andaluza.png", new BigDecimal("5.99"), 90, new BigDecimal("10.23"), hamaProductFamilyRepository.findByFamilyName("ALIMENTOS").get(), hamaProductRateRepository.findById(3L).get(), hamaProviderRepository.findById(8L).get() , new ArrayList<>()));

			hamaProductRepository.save(new HamaProduct(null, "PS5 y 5 juegos de God", "Consola de última generación.", "ps5.jpg", new BigDecimal("499.99"), 50, new BigDecimal("15.00"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(),hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(2L).get() ,new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Switch y 6 juegos de Zelda", "Consola portátil para disfrutar en cualquier lado.", "switch.jpg", new BigDecimal("299.99"), 70, new BigDecimal("10.11"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(7L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "iPhone viejo pero bonito", "Última versión del smartphone de Apple.", "iphone.jpg", new BigDecimal("999.99"), 40, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(11L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Laptop cuantica universal", "Ordenador portátil de alto rendimiento.", "laptop.jpg", new BigDecimal("799.99"), 60, new BigDecimal("10.25"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(2L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "TV de 20 pulgadas que crece", "Televisor 4K UHD con HDR.", "tv.jpg", new BigDecimal("599.99"), 90, new BigDecimal("10.15"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(),hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(7L).get() ,new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Android sin sistema OS", "Smartphone con sistema operativo Android.", "android.jpg", new BigDecimal("399.99"), 100, new BigDecimal("15.30"), hamaProductFamilyRepository.findByFamilyName("TECNOLOGIA").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(11L).get() , new ArrayList<>()));

			hamaProductRepository.save(new HamaProduct(null, "Chanel de 1990", "Perfume de lujo para ocasiones especiales.", "chanel.jpg", new BigDecimal("89.99"), 80, new BigDecimal("10.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(3L).get(), hamaProviderRepository.findById(12L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Boss de 2024", "Aroma sofisticado y elegante.", "boss.jpg", new BigDecimal("79.99"), 100, new BigDecimal("15.25"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(4L).get(), hamaProviderRepository.findById(15L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Bronco de 2013", "Fragancia masculina con carácter.", "bronco.jpg", new BigDecimal("59.99"), 120, new BigDecimal("10.25"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(3L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Pagan de 2015", "Perfume místico y envolvente.", "pagan.jpg", new BigDecimal("69.99"), 150, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(12L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Opium de 2025", "Fragancia exótica y misteriosa.", "opium.jpg", new BigDecimal("99.99"), 60, new BigDecimal("11.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(3L).get(), hamaProviderRepository.findById(15L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Rosa de 2010", "Aroma floral y delicado.", "rosa.jpg", new BigDecimal("49.99"), 110, new BigDecimal("20.00"), hamaProductFamilyRepository.findByFamilyName("PERFUMERIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(3L).get() , new ArrayList<>()));

			hamaProductRepository.save(new HamaProduct(null, "Reloj de Mano", "Elegante reloj de acero inoxidable.", "reloj.jpg", new BigDecimal("199.99"), 50, new BigDecimal("10.50"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(4L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Pulsera bien bonita", "Pulsera de plata con diseño minimalista.", "pulsera2.jpg", new BigDecimal("79.99"), 90, new BigDecimal("15.00"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(9L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Pulsera bien fea", "Brazalete de oro con incrustaciones.", "pulsera.jpg", new BigDecimal("249.99"), 30, new BigDecimal("10.40"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(4L).get(), hamaProviderRepository.findById(13L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Collar de fantasia", "Collar de diamantes finamente elaborado.", "collar.jpg", new BigDecimal("299.99"), 20, new BigDecimal("15.00"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(4L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Aretes que no cuelgan", "Aretes de oro con piedras preciosas.", "aretes.jpg", new BigDecimal("99.99"), 50, new BigDecimal("10.8"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(9L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Aretes que si cuelgan", "Aretes de plata con diseño elegante.", "aretes1.jpg", new BigDecimal("89.99"), 60, new BigDecimal("10.11"), hamaProductFamilyRepository.findByFamilyName("JOYERIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(13L).get() , new ArrayList<>()));

			hamaProductRepository.save(new HamaProduct(null, "Camisa de Marino", "Camisa de algodón para hombre.", "camisa.jpg", new BigDecimal("29.90"), 100, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(5L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Pantalón de Policia", "Pantalón de mezclilla de corte clásico.", "jean.jpg", new BigDecimal("39.90"), 150, new BigDecimal("10.60"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(4L).get(), hamaProviderRepository.findById(8L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Botas de Toro", "Botas de cuero negro de alta calidad.", "botas.jpg", new BigDecimal("59.90"), 200, new BigDecimal("10.80"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(14L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Blusa para Mujer", "Blusa para chica de verano.","blusa.jpg", new BigDecimal("19.90"), 180, new BigDecimal("10.90"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(2L).get(), hamaProviderRepository.findById(5L).get() , new ArrayList<>()));
			hamaProductRepository.save(new HamaProduct(null, "Cinturo bonito", "Cinturón del mejor cuero Cubano.", "cinturon.jpg", new BigDecimal("14.90"), 300, new BigDecimal("5.00"), hamaProductFamilyRepository.findByFamilyName("INDUMENTARIA").get(), hamaProductRateRepository.findById(1L).get(), hamaProviderRepository.findById(8L).get() , new ArrayList<>()));
           
			
			
			
			
			
		
			
			//CUSTOMERS
			//HamaCustomer(Long, String, String, String, String, String, String, HamaUser)
			hamaCustomerRepository.save(new HamaCustomer(null, "Wendy Gabriela Alccantara", "+34 658 149543", "wendy34@gmail.com", 
				    "Pascual Toso 1545 ave.Reyes", "1234567890123456716515", "223",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Rodrigo Alessandro Meneses", "+34 658 949909", "rodtota@gmail.com", 
				    "Calle del Sol 54, Madrid", "1234567890123456761516", "224",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Gorila Fuellez Cantera", "+34 687 955989", "gorilona@gmail.com", 
				    "Calle de la Luna 123, Madrid", "1234567890123456751561", "225",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Carlos Eduardo Silva", "+34 679 125647", "carloseduardo@gmail.com", 
				    "Avenida de los Reyes 25, Madrid", "12345678901234567561615", "226",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Laura Martín López", "+34 666 987654", "laura.martin@gmail.com", 
				    "Calle Mayor 14, Madrid", "1234567890123456751651", "227",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Alfredo Jiménez Pérez", "+34 634 456123", "alfredo.jimenez@gmail.com", 
				    "Calle Gran Vía 78, Madrid", "123456789012345675615615", "228", null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Isabel García Sánchez", "+34 620 345678", "isabel.garcia@gmail.com", 
				    "Calle de la Paz 3, Madrid", "123456789012964156515", "229",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Miguel Ángel Gómez", "+34 612 876543", "miguel.gomez@gmail.com", 
				    "Calle del Prado 21, Madrid", "1234567890123456715615", "230",null));

				hamaCustomerRepository.save(new HamaCustomer(null,"Ana María López", "+34 699 654321", "ana.lopez@gmail.com", 
				    "Calle del Lago 19, Madrid", "12345678901234567156", "231", null));

				hamaCustomerRepository.save(new HamaCustomer(null,"José Luis Martín", "+34 690 123987", "joseluis.martin@gmail.com", 
				    "Calle de la Sierra 8, Madrid", "123456789012345678191", "232",null));

		
				//CUSTOMERS ORDERS
				
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 15, 10, 30, 0), "Progress", hamaCustomerRepository.findById(1L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null,LocalDateTime.of(2025, 2, 16, 14, 0, 0), "Finished", hamaCustomerRepository.findById(2L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null,LocalDateTime.of(2025, 2, 17, 9, 15, 0), "Progress", hamaCustomerRepository.findById(3L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null,LocalDateTime.of(2025, 2, 18, 18, 45, 0), "Finished", hamaCustomerRepository.findById(4L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null,LocalDateTime.of(2025, 2, 19, 11, 0, 0), "Progress", hamaCustomerRepository.findById(5L).get(), null));
			
				// Para Wendy Gabriela Alcántara
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 15, 10, 30, 0), "Progress", hamaCustomerRepository.findById(1L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 10, 14, 45, 0), "Finished", hamaCustomerRepository.findById(1L).get(), null));

				// Para Rodrigo Alessandro Meneses
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 20, 11, 15, 0), "Progress", hamaCustomerRepository.findById(2L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 18, 9, 50, 0), "Finished", hamaCustomerRepository.findById(2L).get(), null));

				// Para Gorila Fuellez Cantera
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 1, 5, 8, 0, 0), "Progress", hamaCustomerRepository.findById(3L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 25, 17, 30, 0), "Finished", hamaCustomerRepository.findById(3L).get(), null));

				// Para Carlos Eduardo Silva
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 1, 13, 20, 0), "Progress", hamaCustomerRepository.findById(4L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 22, 16, 10, 0), "Finished", hamaCustomerRepository.findById(4L).get(), null));

				// Para Laura Martín López
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 7, 9, 45, 0), "Progress", hamaCustomerRepository.findById(5L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 14, 12, 30, 0), "Finished", hamaCustomerRepository.findById(5L).get(), null));

				// Para Alfredo Jiménez Pérez
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 1, 12, 10, 15, 0), "Progress", hamaCustomerRepository.findById(6L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 28, 14, 55, 0), "Finished", hamaCustomerRepository.findById(6L).get(), null));

				// Para Isabel García Sánchez
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 2, 7, 20, 0), "Progress", hamaCustomerRepository.findById(7L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 8, 18, 40, 0), "Finished", hamaCustomerRepository.findById(7L).get(), null));

				// Para Miguel Ángel Gómez
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 9, 15, 30, 0), "Progress", hamaCustomerRepository.findById(8L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 11, 11, 25, 0), "Finished", hamaCustomerRepository.findById(8L).get(), null));

				// Para Ana María López
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 1, 19, 8, 10, 0), "Progress", hamaCustomerRepository.findById(9L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 2, 23, 14, 5, 0), "Finished", hamaCustomerRepository.findById(9L).get(), null));

				// Para José Luis Martín
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 1, 29, 9, 55, 0), "Progress", hamaCustomerRepository.findById(10L).get(), null));
				hamaCustomerOrderRepository.save(new HamaCustomerOrder(null, LocalDateTime.of(2025, 3, 5, 16, 20, 0), "Finished", hamaCustomerRepository.findById(10L).get(), null));

			
		};
	}
		
}
