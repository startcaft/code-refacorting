package test;

import org.junit.Test;
import source.refacotring03.Customer_Recactoring03;
import source.refacotring03.Movie_Refacorting03;
import source.refacotring03.NewReleasePrice;
import source.refacotring03.Price;
import source.refacotring03.RegularPrice;
import source.refacotring03.Rental_Refacoting03;

public class TestRefacoting03 {
	
	@Test
	public void testStatement(){
		
		Price price = new RegularPrice();
		Customer_Recactoring03 customer = new Customer_Recactoring03("张三");
		Movie_Refacorting03 m1 = new Movie_Refacorting03("湄公河行动", price);
		Rental_Refacoting03 r1 = new Rental_Refacoting03(m1, 3);
		
		price = new NewReleasePrice();
		Movie_Refacorting03 m2 = new Movie_Refacorting03("七月与安生", price);
		Rental_Refacoting03 r2 = new Rental_Refacoting03(m2, 2);
		
		customer.getRentals().add(r1);
		customer.getRentals().add(r2);
		
		System.out.println(customer.statement());
	}
}
