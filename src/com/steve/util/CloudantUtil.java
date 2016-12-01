package com.steve.util;


import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import net.sf.json.JSONObject;

public class CloudantUtil {
	
	private static CloudantClient client = null;
	
	public static Database getDatabase() throws Exception {
		String db_name = "dashboard_db";//"test1";
		
		Database db;

		CloudantClient client = getClientConnectorInstance();
		db = client.database(db_name, false);
		
		return db;
	}
	
	public static Database getDatabase(String database) throws Exception{

		CloudantClient client = ClientBuilder
				.url(new URL(PropertiesReader.getProperty("c_url")))
				.username(PropertiesReader.getProperty("c_username"))
				.password(PropertiesReader.getProperty("c_password")).build();
		Database db = client.database(database, true);
//		client.shutdown();
		return db;
	}
	
	public static CloudantClient getClientConnectorInstance() {
		try {
			if (client == null) {
				client = cloudantClientConnection();
			}

		} catch (Exception ce) {
		}
		return client;
	}
	
	/*
	 * This method creates connection to the cloudant database.
	 */
	public static CloudantClient cloudantClientConnection() throws Exception{
		
		try {
			String account = "0f95a669-6c76-4c49-ac6a-fca302a549b1-bluemix";//"macitest";
			String username = "0f95a669-6c76-4c49-ac6a-fca302a549b1-bluemix";//"macitest";
			String password = "eb7cced75a2ed40ef1a79689d3cd381d03e3483a9b820814ede3f59f175a4b9b";//"babybee123";
			String connections = "10";
		
			client = ClientBuilder.account(account).username(username).password(password).maxConnections(Integer.parseInt(connections))
					.customSSLSocketFactory(SSLContext.getInstance("default").getSocketFactory()).build();
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public static void main(String args[]){
		try {
			
			JSONObject json = new JSONObject();
			json.put("_id", "dashboard_data");
			json.put("_rev", "4-8bf0991beca5ba932502c1c7319919ab");
//			json.put("_rev", "");
			json.put("ud", "hello");
			json.put("sd", "world");
			
//			String database = PropertiesReader.getProperty("c_db_name");
//			Database db = getDatabase(database);
//			db.save(json);
			
			Database db = getDatabase();
			db.update(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
