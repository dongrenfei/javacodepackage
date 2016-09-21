package core.java.ch04;

import java.util.Arrays;

public class CardGame {

	public static void main(String[] args) {
		CardDeck cd = new CardDeck();
//		cd.displayCardDeck();

		cd.shuffle();
		cd.displayCardDeck();
		
		int[][] luckNum = {{1,2}, {2,3},{4, 5}, {6,7}, {8,11}, {9,13}};
		String s = Arrays.deepToString(luckNum);
		System.out.println(s+" | "+CardGame.class);
	}

}
