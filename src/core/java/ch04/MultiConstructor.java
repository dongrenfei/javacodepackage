package core.java.ch04;

public class MultiConstructor {
	private String name;
	private double salary;
	
	public MultiConstructor(String n, double s) {
		name = n;
		salary = s;
	}
	
	public MultiConstructor(double s) {
		this("default", s);
	}
	
	public MultiConstructor(String n) {
		this(n, 50000);
	}
}
