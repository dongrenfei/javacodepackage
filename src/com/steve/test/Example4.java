package com.steve.test;

public class Example4 implements Cloneable {
	Example4() {
		System.out.println("Created by invoking newInstance()");
	}
	
	Example4(String msg) {
		System.out.println(msg);
	}
	
	public static void main(String[] args) throws ClassNotFoundException,
	InstantiationException, IllegalAccessException, CloneNotSupportedException {
		// create a new Example4 object with the new operator
		Example4 obj1 = new Example4("Created with new.");
		
		// get a reference to the Class instance for Example4, then
		// invoke newInstance() on it to create a new Example4 object
		Class myClass = Class.forName("com.steve.test.Example4");
		Example4 obj2 = (Example4) myClass.newInstance();
		
		// make an identical copy of the second Example4 object
		Example4 obj3 = (Example4) obj2.clone();
	}
}
