package com.steve.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFormat implements FileHandleBehavior {
	private String inputFullPath;
	private String otputFullPath;

	public JsonFormat(String fileFullPath, String otputFullPath) {
		this.inputFullPath = fileFullPath;
		this.otputFullPath = otputFullPath;
	}

	@Override
	public String handle() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(this.inputFullPath)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.otputFullPath)));
			String temp = null;
			StringBuffer sbf = new StringBuffer();

			while ((temp = br.readLine()) != null) {
				sbf.append(temp).append(",");
				bw.write(temp+",");
			}

			br.close();
			bw.close();
			
			System.out.println("handle done."+sbf.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void main(String[] args) {
		String input = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\wpdata 2016-11-22.metatopics.json";
		String otput = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\wpdata 2016-11-22.metatopics.new.json";
		JsonFormat js = new JsonFormat(input, otput);
		js.handle();
		
		input = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\wpdata 2016-11-22.topics.json";
		otput = "C:\\Users\\IBM_ADMIN\\Desktop\\ThirdpartyData\\wpdata 2016-11-22.topics.new.json";
		
		js = new JsonFormat(input, otput);
		js.handle();
	}

}
