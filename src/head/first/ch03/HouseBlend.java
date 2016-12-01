package head.first.ch03;

public class HouseBlend extends Beverage {
	public HouseBlend() {
		desc = "House Blend Coffee";
	}

	@Override
	public double cost() {
		return 0.89;
	}

}
