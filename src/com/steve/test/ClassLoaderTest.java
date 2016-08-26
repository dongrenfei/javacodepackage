package com.steve.test;

import java.net.URL;

public class ClassLoaderTest {

	public static void main(String[] args) {
		/**
		 * BootStrap ClassLoader����Ϊ���������������Java����ز�����������������
		 * �������JDK�еĺ�����⣬�磺rt.jar��resources.jar��charsets.jar�ȣ�
		 * ��ͨ�����³����ø������������Щ�ط���������ص�jar��class�ļ� 
		 */
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
		    System.out.println(urls[i].toExternalForm());
		}
		System.out.println(System.getProperty("sun.boot.class.path"));
	}

}
