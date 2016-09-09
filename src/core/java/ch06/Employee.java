package core.java.ch06;

public class Employee implements Comparable<Employee> {
	private String name;
	private double salary;
	
	public Employee(String n, double s) {
		name = n;
		salary = s;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary*byPercent;
		salary += raise;
	}
	
	/**
	 * compares employees by salary
	 * @param other another Employee object
	 * @return a negative value if this employee has a lower 
	 * salary than otherObject, 0 if the salaries are same,
	 * a positive value otherwise
	 * */
	public int compareTo(Employee other) {
		return Double.compare(salary, other.getSalary());
	}
}
