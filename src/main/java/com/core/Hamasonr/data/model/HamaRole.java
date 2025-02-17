package com.core.Hamasonr.data.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class HamaRole implements Serializable {

	// El AllArgsConstructor no agregar los atributos final o static.
	// Es un identificardor único que se utiliza por si se hacen cambios en la clase
	// de atributos por ejemplo para que la JVM no le asigne un random y genere
	// conflicto
	private static final long serialVersionUID = 1L;

	@Id
	private String rolename;

}
