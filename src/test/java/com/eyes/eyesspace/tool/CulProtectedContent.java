package com.eyes.eyesspace.tool;

import com.eyes.eyesspace.utils.CryptUtils;
import org.junit.jupiter.api.Test;

/**
 * @author eyesYeager
 * data 2025/9/1 20:03
 */

public class CulProtectedContent {

	private static final String key = "1234567890123456";

	@Test
	public void gen() throws Exception {
		String content = """
以前觉得钱不就是拿来花的，但我现在觉得，钱拿来看也挺好的。。。。

很小的时候看过一个故事，说古代有个很吝啬的人，吃饭都舍不得吃菜，就挂了一块咸菜到房梁上，每次吃饭都看几眼，这样既不用消耗菜还能想象到咸味。

我小时候特别不理解，觉得这只是个夸大的讽刺笑话，但现在我懂了，我的钱现在舍不得花，每次打开手机看眼数字就能很开心。。。。。
		""";
		System.out.println(CryptUtils.aesEncrypt(content, key));
	}

	@Test
	public void check() throws Exception {
		String content = "Opm9VHGb93uZ2HEbNSWrWY9TOkZm6mkhWyNBxyUG0eoXjbIe+nEP8rE/LCwrVQ6LQ6tjfITZkTlp3IpzaPIZJPT54oR+VOOIEbB8UfSiCGiM8d2Btv10BaYf4jeogSsDu81A5w7S+//kmglP8f3Bl5ebxcyI5BotNnY/8XpW591Hq9OZ/2gvR4U63dQucWaThN3mIwT0WAozlGwjkRfGn9ndWz3LhuP9L0ZinIRE5eE7zG1QnqHNPE/aLR/H/MNh1RATss+ZkOx8UbWHh1yRKfN/hUIhm+ANj2i5s597W2tNp2CLqe3RUT4MoBsFCr1KAx20z3XcTFGh7LjwgHS6Osfrf6SlExgEuDJzwS7B8Pg0J2+Ed5tyvh3MyBVkw/2QOFHY0tvOfYK9rklILj6+Q7foPxFAUZBLXKrORtMyoUPW0E2mpw5suDp1dQ7qu2hpShSBY3LWpWNgWZaLivxIvWxZ4l1ODoQ2SoVn/EAv/PQinFk4VxUIrBkOyY5Jxn4BsJmnexaVUIWWfuuGpbsHr2lPauE7vMaK9161OUIHcIQRmFYz4a9OWo0bFCdA+Tzj/aCdidD1ai1ORLGdSTpEcbq2QMOdAH8QepdbnLetY+zol/YlsmpxGe+yxLslpV0PHFc1Uk8Brt+pXNlVzyH3pg==";
		String s = CryptUtils.aesDecrypt(content, key);
		System.out.println(s);
	}
}
