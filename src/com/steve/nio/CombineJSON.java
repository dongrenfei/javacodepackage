package com.steve.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.json.JSONObject;

public class CombineJSON implements FileHandleBehavior {
	private String afileFullPath;// input file 1
	private String bfileFullPath;// input file 2
	private String cfileFullPath;// output file
	
	public CombineJSON(String a, String b, String c) {
		this.afileFullPath = a;
		this.bfileFullPath = b;
		this.cfileFullPath = c;
	}
	
	@Override
	public String handle() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(this.afileFullPath)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.cfileFullPath)));
			String temp = null;
			StringBuffer sbf = new StringBuffer();
			JSONObject jo = new JSONObject();

			// combine file a
			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}
			jo.put("metatopic", sbf.toString());
			
			// combine file b
			br = new BufferedReader(new FileReader(new File(this.bfileFullPath)));
			temp = null;
			sbf = new StringBuffer();
			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
			}
			jo.put("topic", sbf.toString());
			
			// output result
			bw.write(jo.toString());
			bw.close();
			
			System.out.println("JSON combined ===>"+this.cfileFullPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.cfileFullPath;
	}

}
