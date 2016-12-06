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
//				bw.write(temp+",");
			}

			String content = "["+sbf.toString().substring(0, sbf.toString().length()-1) + "]";
			bw.write(content);
			br.close();
			bw.close();
			
			System.out.println(this.otputFullPath+" reformat done.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.otputFullPath;
	}

}
