package com.core.Hamasonr.data.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HamaLogin {
	
	//Etiqueta de validación para controlar la cantidad de caractéres máximos y mínimos
		@Size(min=3, max=50, message="{model.data.validation.HamaLogin.username.size}")
		// Reg Exp: Expresiones regulares.
		@Pattern(regexp = "[A-Za-z0-9_-]{3,50}", message = "{model.data.validation.HamaLogin.username.pattern}")
		private String hamaUsername;
		
		//Es importante tener en cuenta que la contraseña se va a cifrar.
		
		@Size(min=5, max=255, message="{model.data.validation.HamaLogin.password}")
		private String hamaPassword;

}

