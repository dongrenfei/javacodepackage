package com.steve.test;

import java.net.URL;

public class ClassLoaderTest {

	public static void main(String[] args) {
		/**
		 * BootStrap ClassLoader：称为启动类加载器，是Java类加载层次中最顶层的类加载器，
		 * 负责加载JDK中的核心类库，如：rt.jar、resources.jar、charsets.jar等，
		 * 可通过如下程序获得该类加载器从哪些地方加载了相关的jar或class文件 
		 */
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
		    System.out.println(urls[i].toExternalForm());
		}
		System.out.println(System.getProperty("sun.boot.class.path"));
	}

}
