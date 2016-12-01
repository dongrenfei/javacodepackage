package head.first.ch03;

public class Milk extends CondimentDecorator {
	Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDesc() {
		return beverage.getDesc() + ", Milk";
	}

	@Override
	public double cost() {
		return 0.10 + beverage.cost();
	}

}
