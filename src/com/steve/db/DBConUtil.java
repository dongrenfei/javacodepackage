package com.steve.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;

import com.steve.util.PropertiesReader;

public class DBConUtil {
	public static Connection getDB2Conn() throws MissingResourceException, ClassNotFoundException, SQLException, Exception {
		Connection conn = null;
		String url = null;
		String username = null;
		String password = null;
		String driver = null;
		
		url = PropertiesReader.getProperty("JDBC_URL");
		username = PropertiesReader.getProperty("DB_USERNAME");
		password = PropertiesReader.getProperty("DB_PASSWORD");
		driver = PropertiesReader.getProperty("DRIVER");
	
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		
		System.out.println("DB connection returned.");
		return conn;
	}
}
