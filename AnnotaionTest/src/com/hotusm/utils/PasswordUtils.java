package com.hotusm.utils;

import java.util.List;

import com.hotusm.annctation.UseCase;

public class PasswordUtils {
	
	@UseCase(id=47,description="Password must contains at least on numeric")
	public boolean validatePassword(String password){
		return (password.matches("\\w*\\d\\w*"));
	}
	
	@UseCase(id=48)
	public String encryptPassword(String password){
		return new StringBuilder(password).reverse().toString();
	}
	
	@UseCase(id=49,description="new password can't equal previously used ones")
	public boolean checkForNewPassword(List<String> prevPasswords,String password){
		
		return !prevPasswords.contains(password);
	}
	
	
}
