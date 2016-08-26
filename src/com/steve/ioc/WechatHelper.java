package com.steve.ioc;

public class WechatHelper implements ISendable {

	@Override
	public void send(String message) {
		System.out.println("From wechat: "+message);
	}

}
