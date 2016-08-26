package com.steve.test;

import java.net.URL;
import java.sql.SQLException;
import java.util.MissingResourceException;

import com.steve.db.DBConUtil;

public class Test {

	public static void main(String[] args) {
		
		try {
			DBConUtil.getDB2Conn();
		} catch (MissingResourceException mre) {
			mre.printStackTrace();
			System.out.println("No such property key.");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			System.out.println("DB driver class not found.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("DB connect failed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("other exception.");
		}
	}

}
