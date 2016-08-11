package inside.jvm.ex18;

public class Nostalgia {
	public static int giveMeThatOldFashionedBoolean(boolean bVal) {
		try {
			if(bVal) {
				return 1;
			}
			return 0;
		} finally {
			System.out.println("Got old fashioned.");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("case 1: "+Nostalgia.giveMeThatOldFashionedBoolean(false));
		System.out.println("case 2: "+Nostalgia.giveMeThatOldFashionedBoolean(true));
	}

}
