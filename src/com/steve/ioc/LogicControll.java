package com.steve.ioc;

public class LogicControll {

	public static void main(String[] args) {
		String message = "新年快乐！过节费5000！";
		ISendable greetTool;
//		greetTool = new EmailHelper();
//		greetTool = new TelephoneHelper();
		greetTool = new WechatHelper();
		GreetMessageService service = new GreetMessageService(greetTool);
		service.greet(message);
	}

}
