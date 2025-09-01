package com.eyes.eyesspace.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.security.SecureRandom;

/**
 * @author eyesYeager
 * data 2025/9/1 20:04
 */

public class CryptUtils {

	private static final String AES_ALGORITHM = "AES";

	private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";

	public static String aesEncrypt(String data, String key) throws Exception {
		byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
		SecretKeySpec keySpec = new SecretKeySpec(byteKey, AES_ALGORITHM);
		IvParameterSpec ivSpec = new IvParameterSpec(byteKey);
		Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public static String aesDecrypt(String encryptedData, String key) throws Exception {
		byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
		SecretKeySpec keySpec = new SecretKeySpec(byteKey, AES_ALGORITHM);
		IvParameterSpec ivSpec = new IvParameterSpec(byteKey);
		Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(decrypted, StandardCharsets.UTF_8);
	}
}
