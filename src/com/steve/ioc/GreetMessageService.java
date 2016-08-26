package com.steve.ioc;

public class GreetMessageService {
	ISendable greetTool;
	
	public GreetMessageService(ISendable sendTool) {
		greetTool = sendTool;
	}
	
	public void greet(String message) {
		greetTool.send(message);
	}
}
