package com.steve.nio;

public abstract class FileHandler {
	FileHandleBehavior fhb;
	FileGenerateBehavior fgb;
	
	public void performHandle() {
		fhb.handle();
	}
	
	public void performGenerate() {
		fgb.generate();
	}

}
