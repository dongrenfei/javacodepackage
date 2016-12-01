package head.first.ch03;

public class StarbuzzCoffee {

	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		System.out.println(beverage.getDesc()+" $"+beverage.cost());
		
		Beverage beve2 = new DarkRoast();
		beve2 = new Mocha(beve2);
		beve2 = new Mocha(beve2);
		beve2 = new Whip(beve2);
		System.out.println(beve2.getDesc()+" $"+beve2.cost());
		
		Beverage beve3 = new HouseBlend();
		beve3 = new Soy(beve3);
		beve3 = new Mocha(beve3);
		beve3 = new Whip(beve3);
		System.out.println(beve3.getDesc()+" $"+beve3.cost());
	}

}
