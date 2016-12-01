package head.first.ch03;

public class DarkRoast extends Beverage {
	public DarkRoast() {
		desc = "Dark Roast";
	}

	@Override
	public double cost() {
		return 0.99;
	}

}
