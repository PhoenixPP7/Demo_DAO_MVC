package mvc.test;

import mvc.util.MD5Code;

public class TestMD5Code {
	public static void main(String[] args) {
		String str = "java" ;
		String sec = new MD5Code().getMD5ofStr(str) ; 
		System.out.println(sec) ;
		System.out.println(sec.length());
	}
}
