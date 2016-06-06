package com.steve.rest;

import com.steve.md5.MD5Util;

public class Test {

	public static void main(String[] args) {
		String str = "dongrenfei@126.com";
		String md5str = MD5Util.string2MD5(str);
		System.out.println(md5str);
	}

}
