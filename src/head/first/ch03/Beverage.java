package head.first.ch03;

public abstract class Beverage {
	String desc = "Unknown Beverage";
	
	public String getDesc() {
		return desc;
	}
	
	public abstract double cost();
}
