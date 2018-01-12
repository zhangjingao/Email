
package com.zjg.key;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * AES对称加密和解密
 */
public class key {

	public static void main(String[] args) throws Exception {
		String str = "a";
		MessageDigest md5 = MessageDigest.getInstance("MD5");// 指定MD5加密算法
		BASE64Encoder base64en = new BASE64Encoder(); // Base64的编码
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));// 进行哈希计算并返回结果，解释md5码成明文字符串
		MessageDigest md52 = null;     
		   md52 = MessageDigest.getInstance("MD5");     
		  char[] charArray = newstr.toCharArray();     
		  byte[] byteArray = new byte[charArray.length];     
		    
		  for (int i = 0; i < charArray.length; i++)     
		   byteArray[i] = (byte) charArray[i];     
		    
		  byte[] md5Bytes = md52.digest(byteArray);     
		    
		  StringBuffer hexValue = new StringBuffer();     
		    
		  for (int i = 0; i < md5Bytes.length; i++) {     
		   int val = ((int) md5Bytes[i]) & 0xff;     
		   if (val < 16)     
		    hexValue.append("0");     
		   hexValue.append(Integer.toHexString(val));     
		  }     
		System.out.println(newstr + " \n"+hexValue);
	}
}
