package core.java.ch05;

import java.util.Arrays;
import java.util.Random;

public class ArrayTest {

	public static void main(String[] args) {
		Random rd = new Random();
		int size = rd.nextInt(50);
		int[] num = new int[size];
		for(int i=0; i<num.length; i++) {
			num[i] = i;
		}
		System.out.println(Arrays.toString(num));
		
		Size s = Enum.valueOf(Size.class, "SMALL");
		
	}

}
