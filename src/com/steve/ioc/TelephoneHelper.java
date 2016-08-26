package com.steve.ioc;

public class TelephoneHelper implements ISendable {

	@Override
	public void send(String message) {
		System.out.println("From telephone: "+message);
	}

}
