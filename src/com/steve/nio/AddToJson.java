package com.steve.nio;

public class AddToJson extends FileHandler {
	private String input;
	private String output;
	private String content;

	public AddToJson(String inPath, String outPath) {
		this.input = inPath;
		this.output = outPath;
		fhb = new HandleJSON(this.input);
		this.content = fhb.handle();
		fgb = new GenerateJSON(this.content, this.output);
	}
	
}
