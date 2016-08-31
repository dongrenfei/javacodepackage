package core.java.ch04;

import java.util.Date;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 75000, 1990, 3, 15);

		// raise everyone's salary by 5%
		for (Employee e : staff) {
			e.raiseSalary(5);
		}

		// print out information about all Employee objects
		for (Employee e : staff) {
			System.out.println("name= " + e.getName() + ", salary= " + e.getSalary() + ", hireDay= " + e.getHireDay());
		}
		
		Date d = staff[0].getHireDay();
		double tenYearsInMilliSeconds = 10*365.25*24*60*60*1000;
		d.setTime(d.getTime()-(long)tenYearsInMilliSeconds);
		
		System.out.println("add ten years to Carl's working time?: "+staff[0].getHireDay());
		
		System.out.println("equals ? "+staff[0].equals(staff[2]));

		int x = 1 + (int)(Math.random() * 54);
		System.out.println(x);
	}

}
