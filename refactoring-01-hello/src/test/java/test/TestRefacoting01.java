package test;

import org.junit.Test;
import souces.Movie;
import souces.Rental;
import source.refacorting01.Customer_Recactoring01;

public class TestRefacoting01 {
	
	@Test
	public void testStatement(){
		
		Customer_Recactoring01 customer = new Customer_Recactoring01("张三");
		Movie m1 = new Movie("湄公河行动", Movie.REGULAR);
		Rental r1 = new Rental(m1, 3);
		Movie m2 = new Movie("七月与安生", Movie.NEW_RELEASE);
		Rental r2 = new Rental(m2, 2);
		
		customer.getRentals().add(r1);
		customer.getRentals().add(r2);
		
		System.out.println(customer.statement());
	}
}
