package core.java.ch04;

public class CardGame {

	public static void main(String[] args) {
		CardDeck cd = new CardDeck();
//		cd.displayCardDeck();
		
		cd.shuffle();
		cd.displayCardDeck();
	}

}
