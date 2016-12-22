package head.first.ch07;

public class NewTea extends CaffeineBeverage {

	@Override
	void brew() {
		System.out.println("Steeping the tea");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding Lemon");
	}

}
