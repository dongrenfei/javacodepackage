package inside.jvm.ex18;

public class Surprise {
	public static boolean surpriseTheProgrammer(boolean bVal) {
		while(bVal) {
			try {
				return true;
			} finally {
				break;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("case 1: "+Surprise.surpriseTheProgrammer(false));
		System.out.println("case 2: "+Surprise.surpriseTheProgrammer(true));
	}
}
