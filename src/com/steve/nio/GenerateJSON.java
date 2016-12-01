package com.steve.nio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateJSON implements FileGenerateBehavior {
	private String content;
	private String outPath;
	
	public GenerateJSON(String c, String op) {
		this.content = c;
		this.outPath = op;
	}
	
	@Override
	public void generate() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.outPath)));
			bw.write(this.content);
			bw.close();
			
			System.out.println("json file generated.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
