package com.core.Hamasonr.service;


import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.core.Hamasonr.data.model.HamaEncryption;



public interface IHamaEncryptionService {
	
	public HamaEncryption newEntity();
	
	public HamaEncryption encryptText(HamaEncryption encryption);
	public List<String> findAllEncodersName();
	
	public List<PasswordEncoder>getEncoderList();

}
