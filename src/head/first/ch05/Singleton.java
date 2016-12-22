package head.first.ch05;

public class Singleton {
	private static Singleton uniqueInstance = new Singleton();
	
	private Singleton() {}
	
	public static Singleton getInstance() {
//		if(uniqueInstance == null) {
//			uniqueInstance = new Singleton();
//		}
		
		return uniqueInstance;
	}
}
