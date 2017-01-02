package test;

import org.junit.Test;
import souces.Movie;
import source.refacotring02.Customer_Recactoring02;
import source.refacotring02.Rental_Refacoting02;

public class TestRefacoting02 {
	
	@Test
	public void testStatement(){
		
		Customer_Recactoring02 customer = new Customer_Recactoring02("张三");
		Movie m1 = new Movie("湄公河行动", Movie.REGULAR);
		Rental_Refacoting02 r1 = new Rental_Refacoting02(m1, 3);
		Movie m2 = new Movie("七月与安生", Movie.NEW_RELEASE);
		Rental_Refacoting02 r2 = new Rental_Refacoting02(m2, 2);
		
		customer.getRentals().add(r1);
		customer.getRentals().add(r2);
		
		System.out.println(customer.statement());
	}
}
