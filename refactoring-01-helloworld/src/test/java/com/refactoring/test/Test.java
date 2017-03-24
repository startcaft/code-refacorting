package com.refactoring.test;

import java.util.Vector;

import com.startcaft.www.refactoring_01_helloworld.Customer;
import com.startcaft.www.refactoring_01_helloworld.Movie;
import com.startcaft.www.refactoring_01_helloworld.Rental;

public class Test {
	
	@org.junit.Test
	public void test(){
		
		Movie m1 = new Movie("西游附魔篇", Movie.REGULAR);
		Movie m2 = new Movie("一条狗的使命", Movie.NEW_RELEASE);
		Movie m3 = new Movie("麦兜响当当", Movie.CHILDRENS);
		
		Rental r1 = new Rental(m1, 3);
		Rental r2 = new Rental(m2, 2);
		Rental r3 = new Rental(m3, 1);
		Vector<Rental> v = new Vector<>();
		v.add(r1);
		v.add(r2);
		v.add(r3);
		
		Customer c = new Customer("startcaft");
		c.setRentals(v);
		
		String result = c.statement();
		System.out.println(result);
	}
}
