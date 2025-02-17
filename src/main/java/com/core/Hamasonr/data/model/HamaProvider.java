package com.core.Hamasonr.data.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "PROVIDERS")

@Getter
@Setter
@ToString
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class HamaProvider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;

	@NotNull
	@Column(nullable = false, columnDefinition = "VARCHAR(150) CHECK(LENGTH(PROVIDERNAME) >= 10)") // queremos que la
																										// columna no
																										// sea anulable
																										// . Si usamos
																										// name="nombre"
																										// se le asigna
																										// este nombre a
																										// esta columna
																										// name. en la
																										// tabla
	@Size(min = 10, max = 150, message = "{model.data.validation.HamaProvider.providername}") // Name must be from 10 to
																								// 150 characters
	private String providerName;

	@Column(columnDefinition = "VARCHAR(30) CHECK(LENGTH(PROVIDERCIF) >= 9)")
	@Size(min = 9, max = 30, message = "{model.data.validation.HamaProvider.cif}") // Cif must be from 9 to 30
																					// characters
	private String providerCif;

	@Column(columnDefinition = "CHAR(18) CHECK(LENGTH(PROVIDERPHONE) >= 9)")
	@Size(min = 9, max = 20, message = "{model.data.validation.HamaProvider.phone}") // Phone must have from 9 up to 20
																						// characters
	@NotNull
	private String providerPhone;

	@Email
	@Column(name = "PROVIDERS_EMAIL")
	@Size(min = 3, max = 255, message = "{model.data.validation.HamaProvider.email}") // email must have from 3 up to
																						// 255 characters
	private String providerEmail;

	@Column(columnDefinition = "CHAR(100) CHECK(LENGTH(PROVIDERADDRESS) >= 10)")
	@Size(min = 10, max = 100, message = "{model.data.validation.HamaProvider.address}") // Address must have from 10 up
																							// to 100 characters
	@NotNull
	private String providerAddress;

	@ToString.Exclude
	@OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HamaProduct> providerProducts; // Listado de familias de productos
	
	
}
