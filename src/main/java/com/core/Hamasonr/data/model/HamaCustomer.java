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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="CUSTOMERS")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j


public class HamaCustomer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;
	
	@NotNull
	@Column(nullable=false, columnDefinition="VARCHAR(150)  CHECK(LENGTH(FULLNAME) >= 10)")//queremos que la columna no sea anulable . Si usamos name="nombre" se le asigna este nombre a esta columna name. en la tabla
	@Size(min=10,max=150, message="{model.data.validation.HamaCustomer.fullname}") //Name must be from 10 to 150 characters
	private String fullname;
	
	@Column(columnDefinition="CHAR(18) CHECK(LENGTH(PHONENUMBER) >= 9)")
	@Size(min=9 ,max=20, message="{model.data.validation.HamaCustomer.phoneNumber}") //Phone must have from 9 to 20 characters
	@NotNull
	private String phoneNumber; 
	
	@Email
	@Size(min=3, max=255,message="{model.data.validation.HamaCustomer.email}")//email must have from 3 to 255 characters
	private String email;
	
	@Column(columnDefinition="CHAR(50) CHECK(LENGTH(ADDRESS) >= 20)")
	@Size(min=10 ,max=100, message="{model.data.validation.HamaCustomer.address}") //Address must have from 10 to 100 characters
	@NotNull
	private String address; 
	
	@Column(columnDefinition="CHAR(80) CHECK(LENGTH(CARDNUMBER) >= 28)")
	@Size(min=10 ,max=28, message="{model.data.validation.HamaCustomer.cardnumber}") //Card Number must have from 10 up to 20 numbers
	@NotNull
	private String cardnumber; 
	
	@Column(columnDefinition="CHAR(18) CHECK(LENGTH(EXPIRATIONDATE) >= 3)")
	@Size(min=3 ,max=3, message="{model.data.validation.HamaCustomer.expirationdate}") //Expiration date on the card can only have 3 digits
	@NotNull
	private String expirationdate; 
	
	
	@OneToMany(mappedBy = "hamaCustomer", cascade = CascadeType.ALL, orphanRemoval = true) // El nombre de la propiedad en Order que hace referencia a HamaCustomer
	private List<HamaCustomerOrder> hamaCustomerOrders; // Lista de órdenes asociadas al cliente
}
