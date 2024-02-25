package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptionService {
	private static final String SECRETE_KEY = "Q6DlhQYrJL4YHMsRKeAknQ=="; // encrypted key

	private static final String ALGORITHM = "AES";

	private static SecretKey secretKey;

	private static Cipher cipher;

	static {
		secretKey = new SecretKeySpec(SECRETE_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

		try {
			cipher = Cipher.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String data) throws Exception {
		byte[] encryptedData = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public static String decrypt(String encryptedData) throws Exception {
		byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(decryptedData, StandardCharsets.UTF_8);
	}

	public static SecretKey generateAesKey() throws NoSuchAlgorithmException {
		 int keyLength = 128;
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		return keyGenerator.generateKey();
	}

}
