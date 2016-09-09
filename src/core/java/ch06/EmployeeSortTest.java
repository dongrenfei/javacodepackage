package core.java.ch06;

import java.util.Arrays;

/**
 * This program demonstrates the use of the Comparable interface.
 * @version 1.30 2004-02-27
 * @author Cay Horstmann
 * 
 * */
public class EmployeeSortTest {

	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Harry", 3500);
		staff[1] = new Employee("Carl", 7500);
		staff[2] = new Employee("Tony", 3800);
		
		Arrays.sort(staff);
		
		// print out information about all Employee objects
		for(Employee e : staff) {
			if(e instanceof Comparable) {
				System.out.println("name="+e.getName()+",salary="+e.getSalary());
			}
		}
	}

}
