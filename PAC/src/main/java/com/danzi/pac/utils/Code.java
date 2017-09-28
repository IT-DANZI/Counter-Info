package com.danzi.pac.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * Describe ：随机数获取 EG:验证码
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/12.
 */

public class Code {
	private final static int OFFSET = 538309;

	public static String MobileVfCode() {
		long seed = System.currentTimeMillis() + OFFSET;
		SecureRandom secureRandom = null; // 安全随机类
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seed);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String codeList = "1234567890"; // 验证码数字取值范围
		String sRand = ""; // 定义一个验证码字符串变量

		for (int i = 0; i < 6; i++) {
			int code = secureRandom.nextInt(codeList.length() - 1); // 随即生成一个0-9之间的整数
			String rand = codeList.substring(code, code + 1);
			sRand += rand; // 将生成的随机数拼成一个六位数验证码
		}
		return sRand; // 返回一个六位随机数验证码

	}
}
