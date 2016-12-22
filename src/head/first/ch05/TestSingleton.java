package head.first.ch05;

public class TestSingleton {

	public static void main(String[] args) {
//		Singleton s1 = Singleton.getInstance();
//		Singleton s2 = Singleton.getInstance();
//		
//		System.out.println(s1==s2);
//		System.out.println(s1.equals(s2));
//		System.out.println(s1.hashCode());
//		System.out.println(s2.hashCode());
		System.out.println("======================");
		for(int i=0; i<10; i++) {
			new Thread(new Runnable() {
				public void run() {
					System.out.println(Singleton.getInstance().hashCode());
				}
			}).start();
		}
	}

}
