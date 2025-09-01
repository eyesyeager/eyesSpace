package com.eyes.eyesspace.tool;

import com.eyes.eyesspace.utils.CryptUtils;
import org.junit.jupiter.api.Test;

/**
 * @author eyesYeager
 * data 2025/9/1 20:03
 */

public class CulProtectedContent {

	private static final String key = "86@e0o8l!3Tc&Kp1";

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
		String content = "WV/GKV8to+TA1IZ61QYfuV4WiEJ5fzxauFd5sVLPNaGREUrteMwNU/i3X05Fu5CWTl3E9Dnu8WzauYRLPcNqSB7v/Yym1WZNnDNPLmCkS6bMGPAqWuL4S4sS+B50PU3Smu/OB1WwCIU5kIacyTAzDmDadu1aRAD21CbamCzcAltWiRMKA6QxrUkqH2QdR7va24FL8yr+W3YjI18yBPqaXbCq4z5mXUn1IkMiaUES0088ZfWRfLMI3IRuEZEryMAYdFgLNQe1oxZNYbroaziA5FD0TQlcA9AwmSVamN5L+m1sHGlHhYydTrvYKgbFdDaCPTU4CBK3WAjzHbzqoULC0MmR8MbeL3nPa5+q2zaGuVEqRWuB1OhZcDzbpltzoSC9eJ6ENBf1fcbBFkRG/B7xyi8wcOJmwPmJ3D+stsgUyLDG/tV+BvCWqUsVGjnG1/kV4QTK/EGSHyD4vv1RX3FWB0aSDLJ2s1NPugbF7+Om5E7l+VCqPBS8xVooA+95G+6Ypnk7NmXV0PId/QEPIQbk1PpOjUvUByFkwSlxHshHWqw2Ps+xuhMLb3sWWBworQ/n2bwOYo93YoYPN2d3z/f4nkIBCRgNv7qyfgHllc8HpLMnq0utOoZV41Mk4FSQ0TaqDugSnGrI9XRSo7gNvww7EA==";
		String s = CryptUtils.aesDecrypt(content, key);
		System.out.println(s);
	}
}
