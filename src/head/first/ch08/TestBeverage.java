package head.first.ch08;

public class TestBeverage {

	public static void main(String[] args) {
		CaffeineBeverage cb = new NewTea();
		cb.prepareRecipe();
		
		cb = new NewCoffee();
		cb.prepareRecipe();
	}

}
