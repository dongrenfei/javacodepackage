package core.java.ch11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandleException {

	public static void main(String[] args) throws FileNotFoundException {
		String fileInPath = "C:\\temp\\test.txt";
		try(Scanner in = new Scanner(new FileInputStream(fileInPath));
				PrintWriter out = new PrintWriter("out.txt")) {
			while (in.hasNext()) {
				out.println(in.next().toUpperCase());
			}
		}
		Logger.getGlobal().setLevel(Level.INFO);
		Logger.getGlobal().info("File->Open menu item selected");
	}
}
