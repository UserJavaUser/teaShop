package by.htp.ishop.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class EncodingUtil {
	
	public static byte[] getSalt() {
		SecureRandom random = new SecureRandom();
		byte[]salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	
	public static byte[] getHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 32);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		byte[]hash = factory.generateSecret(spec).getEncoded();
		return hash;
	}
	

}
