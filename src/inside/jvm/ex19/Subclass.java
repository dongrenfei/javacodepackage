package inside.jvm.ex19;

public class Subclass extends Superclass {
	void interestingMethod() {
		System.out.println("Subclass's interesting method.");
	}
	
	public static void main(String args[]) {
		Subclass me = new Subclass();
		me.exampleMethod();
	}
}
