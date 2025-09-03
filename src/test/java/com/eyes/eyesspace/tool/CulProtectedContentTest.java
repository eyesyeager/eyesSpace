package com.eyes.eyesspace.tool;

import com.eyes.eyesspace.utils.CryptUtils;
import org.junit.jupiter.api.Test;

/**
 * @author eyesYeager
 * data 2025/9/1 20:03
 */

public class CulProtectedContentTest {

	private static final String key = "1234567890123456";

	@Test
	public void gen() throws Exception {
		String content = """
		""";
		System.out.println(CryptUtils.aesEncrypt(content, key));
	}

	@Test
	public void check() throws Exception {
		String content = "";
		String s = CryptUtils.aesDecrypt(content, key);
		System.out.println(s);
	}
}
