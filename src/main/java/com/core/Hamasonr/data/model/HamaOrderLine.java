package com.core.Hamasonr.data.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "ORDER_LINES")
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HamaOrderLine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // ID autoincrementado para las líneas de pedido

	@NotNull
	@Min(1) // Mínimo 1 unidad
	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;

	@NotNull
	@DecimalMin(value = "0.00", inclusive = true, message = "{model.data.validation.HamaOrderLine.priceperunit}")
	@Column(name = "PRICE_PER_UNIT", nullable = false, precision = 8, scale = 2) // Hasta 8 dígitos, 2 decimales
	private BigDecimal pricePerUnit;

	@NotNull
	@DecimalMin(value = "5.00", inclusive = true, message = "{model.data.validation.HamaProduct.discount.min}")
	@DecimalMax(value = "100.00", inclusive = true, message = "{model.data.validation.HamaProduct.discount.max}")
	@Column(name = "APPLIED_DISCOUNT", nullable = false, precision = 4, scale = 2) // Hasta 4 dígitos, 2 decimales																				// (porcentaje)
	private BigDecimal discountApplied;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID", nullable = false) // Relación con Orders
	private HamaCustomerOrder hamaCustomerOrder;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false) // Relación con Product
	private HamaProduct product;

}
