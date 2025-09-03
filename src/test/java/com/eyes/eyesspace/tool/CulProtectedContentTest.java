package com.eyes.eyesspace.tool;

import com.eyes.eyesspace.utils.CryptUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author eyesYeager
 * data 2025/9/1 20:03
 */

@SpringBootTest
public class CulProtectedContentTest {

	@Value("${crypt.key}")
	private String key;

	@Test
	public void gen() throws Exception {
		String content = """
		hhh
		""";
		System.out.println(CryptUtils.aesEncrypt(content, key));
		System.out.println(content.length());
	}

	@Test
	public void check() throws Exception {
		String content = "";
		String s = CryptUtils.aesDecrypt(content, key);
		System.out.println(s);
	}
}
