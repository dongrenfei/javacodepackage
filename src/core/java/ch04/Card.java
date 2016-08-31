package core.java.ch04;

/**
 * “ª’≈≈∆
 * */
public class Card {
	private String suit;
	private String value;
	
	public Card(String aSuit, String aValue) {
		suit = aSuit;
		value = aValue;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getSuit() {
		return suit;
	}
}
