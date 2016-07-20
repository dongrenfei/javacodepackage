package com.steve.test;

import java.io.File;
import java.io.FilePermission;
import java.security.Permission;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException {
		char sep = File.separatorChar;
		System.out.println(Class.forName("com.steve.test.Example").getName());
		// read permission for "/tmp/f"
		Permission file = new FilePermission(sep+"tmp"+sep+"f", "read");
		
		// read permission for "/tmp/*", which means
		// all files in the /tmp directory but not any
		// file in subdirctories of /tmp
		Permission star = new FilePermission(sep+"tmp"+sep+"*", "read");
		boolean starImpliesFile = star.implies(file);
		boolean fileImpliesStar = file.implies(star);
		
		// prints "Star implies file=true"
		System.out.println("Star implies file = "+starImpliesFile);
		
		// prints "file implies star = false"
		System.out.println("File implies star = "+fileImpliesStar);
	}

}
