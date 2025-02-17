package com.core.Hamasonr.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "CUSTOMER_ORDERS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j

public class HamaCustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PositiveOrZero
	private Long id;
	
	@Column(name = "CREATION_DATE", nullable = false)
    @NotNull(message = "{model.data.validation.HamaCustomerOrder.creationdate}")
    private LocalDateTime creationDate; // Fecha de creación del pedido
	
	
	 @Column(name = "ORDER_STATUS", nullable = false, length = 10)
	    @NotNull(message = "{model.data.validation.HamaCustomerOrder.OrderStatus.notnull}")
	    @Size(max = 10, message = "{model.data.validation.HamaCustomerOrder.OrderStatus.StatusOptions}")
	    private String orderStatus; // Estado del pedido (Progress o Finished)
	 
	 @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false) // Esta es la columna que referencia a la tabla CUSTOMER
	    private HamaCustomer hamaCustomer; // Esto conecta la orden con un cliente específico.
	 
	 @OneToMany(mappedBy = "hamaCustomerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<HamaOrderLine> orderLines = new ArrayList<>();
	 
	
}
