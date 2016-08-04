package com.steve.test;

public class Dog {
	// how many times this dog wags its tail when saying hello
	private int wagCount = ((int) (Math.random()*5.0)) +1;
	
	void sayHello() {
		System.out.println("Wag");
		for(int i=0; i<wagCount; ++i) {
			System.out.print(", wag");
		}
		System.out.println(".");
	}
	
	public String toString() {
		return "Woof!";
	}
}
