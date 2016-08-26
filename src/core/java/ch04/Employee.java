package core.java.ch04;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee {
	// instance fields
	private String name;
	private double salary;
	private Date hireDay;
	
	// constructor
	public Employee(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar gc = new GregorianCalendar(year, month-1, day);
		hireDay = gc.getTime();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHireDay() {
		//return hireDay;
		return (Date) hireDay.clone();
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public boolean equals(Employee other) {
		return name.equals(other.name);
	}
}
