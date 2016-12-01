package head.first.ch03;

public class Decaf extends Beverage {
	public Decaf() {
		desc = "Decaf Coffee";
	}

	@Override
	public double cost() {
		return 1.05;
	}

}
