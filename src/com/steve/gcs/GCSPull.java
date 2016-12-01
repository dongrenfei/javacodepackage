package com.steve.gcs;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.tivoli.jets.cm.ctrl.ContentHeader;
import com.tivoli.jets.cm.ctrl.ContentMessage;
import com.tivoli.jets.soap.ctrl.client.ControllerSoapClient;

public class GCSPull {

	/**
	 * Example code for extracting published documents content from the Icons
	 * Controller.
	 *
	 */
	public static void retrieveContent() throws Exception {
		// Search PROD
		// String SYSTEM = "CVI Search";
		// String PASSWORD="pa55w0rd";
		// S1 PROD passwrod
		// String SYSTEM = "101";
		// String PASSWORD="s1load3r";
		// Search GS PROD
		// String SYSTEM = "381";
		// String PASSWORD="gbspp88cvi";
		// Search GS TIE
		/// String SYSTEM = "381";
		// String PASSWORD="gbspp33cvi";
		// PP app passwrod
		String SYSTEM = "GBS PP";
		String PASSWORD = "f0rmerlyKV";
		// GS TIE PP password
		// String SYSTEM = "380";
		// String PASSWORD="gbs33pp";
		// GS PROD PP passwor
		// String SYSTEM = "380";
		// String PASSWORD="gbs88pp";
		// String SYSTEM = "SmartSpots";
		// String PASSWORD = "pa$$word";
		// Create a Controller Soap Client object
		// PROD
		// ControllerSoapClient client = new
		// ControllerSoapClient("https://d25httpcl13.con.can.ibm.com/tools/cm/ecm/gcs/servlet/rpcrouter");
		// TIE
		ControllerSoapClient client = new ControllerSoapClient(
				"https://ecmtstcl2.lexington.ibm.com/tools/cm/ecm/gcs/servlet/rpcrouter");
		// String ctype[] = client.getContentTypes(SYSTEM, PASSWORD);
		/*
		 * if (ctype == null || ctype.length == 0) { System.out.println(
		 * "No content types for system " + SYSTEM); return; } else{ for (dint
		 * i=0; i<ctype.length; i++){ System.out.println(ctype[i]); } }
		 */
		String[] synkeys = { "B856188S09328Y16", "E008347U65843T36" };

		ContentMessage cm = new ContentMessage();
		ContentHeader ch = new ContentHeader();
		Writer writer = null;
		System.setProperty("file.encoding", "UTF-8");

		for (String synkey : synkeys) {
			cm = client.getContentBySynKey(SYSTEM, PASSWORD, synkey, true);
			ch = cm.getContentHeader();

			System.out.println(ch.getPubDate());

			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(
								"C:\\MarsWorkspace\\" + synkey + ".xml"),
						"utf-8"));
				writer.write(cm.getContent());
			} catch (IOException ex) {
				// report
			} finally {
				try {
					writer.close();
				} catch (Exception ex) {
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		retrieveContent();
	}
}
