package com.core.Hamasonr.data.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class HamaEncryption {
	
	//Etiqueta de validación para controlar la cantidad de caractéres máximos y mínimos
		@Size(min=1, max=50, message="{model.data.validation.HamaEncryption.texttoencrypt}")
		private String textToEncrypt;
		
		private String passwordEncoderString;
		//Es importante tener en cuenta que la contraseña se va a cifrar.
		
		@Size(min=1, max=255, message="{model.data.validation.HamaEncryption.textencrypted}")
		private String textEncrypted;

}
