package com.steve.ioc;

public class LogicControll {

	public static void main(String[] args) {
		String message = "������֣����ڷ�5000��";
		ISendable greetTool;
//		greetTool = new EmailHelper();
//		greetTool = new TelephoneHelper();
		greetTool = new WechatHelper();
		GreetMessageService service = new GreetMessageService(greetTool);
		service.greet(message);
	}

}
