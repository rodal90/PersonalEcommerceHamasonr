package com.core.Hamasonr.data.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "FAMILY_OF_PRODUCTS")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class HamaProductFamily implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PositiveOrZero
	private Long id;

	@NotNull
	@Column(name = "FAMILYNAME", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'FAMILYNAME'")
	@Size(max = 50, message = "{model.data.validation.HamaProductFamily.familyname}")
	private String familyName;

	//@OneToMany(mappedBy = "productFamily") // Relacionado con el campo "family" de Product
	//private List<HamaProduct> HamaProducts; // Lista de productos en esta familia

	//@ManyToMany(mappedBy = "familyOfProducts") // Mapeo inverso de la relación ManyToMany
	//private List<HamaProvider> HamaProviders; // Lista de proveedores que ofrecen esta familia de productos//

}
