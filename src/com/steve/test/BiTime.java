package com.steve.test;

import java.util.Date;

public class BiTime implements Greeter {
	public void greet() {
		// date's no-arg constructor initializes itself to the current date and time
		Date date = new Date();
		int hours = date.getHours();
		
		// some hours: idnight, 0; noon, 12; 11PM, 23;
		if(hours >=4 && hours <= 11) {
			System.out.println("Good morning, world!");
		} else if(hours >= 12 && hours <= 16) {
			System.out.println("Good afternoon, world!");
		} else if(hours >= 17 & hours <= 21) {
			System.out.println("Good evening, world!");
		} else {
			System.out.println("Good night, world!");
		}
	}
	
	public static void main(String[] args) {
		BiTime bt = new BiTime();
		bt.greet();
	}
}
