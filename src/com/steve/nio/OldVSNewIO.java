package com.steve.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class OldVSNewIO {

	private static final String filePath = "src/com/steve/resource/steve.properties";
	
	public static void main(String[] args) {
		// old io example
//		OldVSNewIO.oldIO();
		System.out.println("====================================================");
		System.out.println("====================================================");
		OldVSNewIO.newIO();
	}

	public static void oldIO() {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			
			byte[] buf = new byte[1024];
			int bytesRead = in.read(buf);
			while(bytesRead != -1) {
				for(int i=0; i<bytesRead; i++) {
					System.out.print((char)buf[i]);
				}
				bytesRead = in.read(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void newIO() {
		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile(filePath,"rw");
			FileChannel fileChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			int bytesRead = fileChannel.read(buf);
			System.out.println("bytesRead="+bytesRead);
			
			while(bytesRead != -1) {
				buf.flip();
				while(buf.hasRemaining()) {
					System.out.print((char)buf.get());
				}
				
				buf.compact();
				bytesRead = fileChannel.read(buf);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(aFile != null) {
					aFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
