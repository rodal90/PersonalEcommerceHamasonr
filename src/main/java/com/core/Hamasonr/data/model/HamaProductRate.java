package com.core.Hamasonr.data.model;


import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "PRODUCTS_RATINGS")

@Slf4j
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HamaProductRate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@PositiveOrZero
	private Long id;
	
	@NotNull
	@Column(name ="RATES",nullable = false, columnDefinition= "VARCHAR(50) DEFAULT 'RATES'")
	@Size(max =50, message= "{model.data.validation.HamaProductRate.ratename}")
	private String rateName;
		
}
