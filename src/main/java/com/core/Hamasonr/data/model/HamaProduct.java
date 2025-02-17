package com.core.Hamasonr.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "Products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class HamaProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;

	@NotNull
	@Column(nullable = false, columnDefinition = "VARCHAR(150)  CHECK(LENGTH(NAME) >= 10)") // queremos que la
																							// columna no
																							// sea anulable
																							// . Si usamos
																							// name="nombre"
																							// se le asigna
																							// este nombre a
																							// esta columna
																							// name. en la
																							// tabla
	@Size(min = 4, max = 150, message = "{model.data.validation.HamaProduct.name}") // Name must be from 10 to 150
																						// characters
	private String name;

	@NotNull
	@Column(nullable = false, columnDefinition = "VARCHAR(150)  CHECK(LENGTH(DESCRIPTION) >= 10)") // queremos que la
																									// columna no sea
																									// anulable . Si
																									// usamos
																									// name="nombre" se
																									// le asigna este
																									// nombre a esta
																									// columna name. en
																									// la tabla
	@Size(min = 20, max = 800, message = "{model.data.validation.HamaProduct.description}")
	private String description;

	@NotNull
	@Column(name = "IMAGES_URL", nullable = false, length = 2083)
//	@Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
	@Size(min = 5, message = "{model.data.validation.HamaProduct.images}")
	private String imageUrl;

	@Column(name = "PRICES", nullable = false, precision = 6, scale = 2)
	@NotNull
	@DecimalMin(value = "0.00", inclusive = true, message = "{model.data.validation.HamaProduct.price.min}")
	@DecimalMax(value = "999.99", inclusive = true, message = "{model.data.validation.HamaProduct.price.max}")
	private BigDecimal price;

	@Column(name = "STOCK", nullable = false)
	@Min(value = 0, message = "{model.data.validation.HamaProduct.stock}")
	private int stock;

	@Column(name = "DISCOUNTS", nullable = false, precision = 4, scale = 2) // Descuento máximo 100.00%
	@DecimalMin(value = "5.00", inclusive = true, message = "{model.data.validation.HamaProduct.discount.min}")
	@DecimalMax(value = "100.00", inclusive = true, message = "{model.data.validation.HamaProduct.discount.max}")
	private BigDecimal discount;

	
	@ManyToOne
	@JoinColumn(name = "FAMILY_ID", nullable = true) // Relaciona con FamilyOfProducts
	private HamaProductFamily productFamily; // Una familia asociada a un producto

	
	@ManyToOne
	@JoinColumn(name = "RATE_ID", nullable = true) // Puede ser nullable si no se asigna de inmediato
	private HamaProductRate rate;
	
	
	@ManyToOne
	@JoinColumn(name = "PROVIDER_ID", nullable = true)
	private HamaProvider provider;


	@ToString.Exclude
	@OneToMany(mappedBy = "product")
	private List<HamaOrderLine> orderLines;

}
