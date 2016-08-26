package com.steve.ioc;

public class EmailHelper implements ISendable {

	@Override
	public void send(String message) {
		System.out.println("From email: "+message);
	}

}
