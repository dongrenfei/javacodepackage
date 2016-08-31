package core.java.ch04;

import java.util.Random;

/**
 * 一副牌
 * 
 * */
public class CardDeck {
	public static final String JOKER = "Joker";
	public static final String RED = "Red";
	public static final String BLACK = "Black";
	String num[] = {"Q", "K", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J"};
    String suit[] = {"Spade","Heart","Diamond","Club"};
	
	private Card[] cards = new Card[54];
	
	public CardDeck() {
		cards[0] = new Card(RED, JOKER);
		cards[1] = new Card(BLACK, JOKER);
		String v = null;
		
		for(int i=2; i<54; i++) {
			cards[i] = new Card(suit[(i-2)/13], num[i%13]);
		}
	}
	
	public void shuffle() {
		Random rd = new Random();
        for(int i=0;i<54;i++)
        {
            int j = rd.nextInt(54);//生成随机数
            Card temp = cards[i];//交换
            cards[i]=cards[j];
            cards[j]=temp;
        }
	}
	
	public Card getTop() {
		return cards[0];
	}
	
	public Card draw() {
		// generate a 1-54 random number
		int x = 1 + (int)(Math.random() * 54);
		return cards[1];
	}
	
	public void displayCardDeck() {
		for(int i=0; i<cards.length; i++) {
			System.out.println(cards[i].getSuit() + " " + cards[i].getValue());
		}
	}
}
