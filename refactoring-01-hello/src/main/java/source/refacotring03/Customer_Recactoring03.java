package source.refacotring03;

import java.util.Enumeration;
import java.util.Vector;

public class Customer_Recactoring03 {
	
	private String name;
	private Vector<Rental_Refacoting03> rentals = new Vector<Rental_Refacoting03>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Rental_Refacoting03> getRentals() {
		return rentals;
	}
	public void setRentals(Vector<Rental_Refacoting03> rentals) {
		this.rentals = rentals;
	}
	
	public Customer_Recactoring03(String name) {
		super();
		this.name = name;
	}
	
	public String statement(){
		String result = "Rental Record for [" + getName() + "]\n";
		Enumeration<Rental_Refacoting03> rentalElements = rentals.elements();
		
		while (rentalElements.hasMoreElements()) {
			Rental_Refacoting03 each = (Rental_Refacoting03) rentalElements.nextElement();
			result += "\t<<" + each.getMovie().getTitle() + ">>\t"
					+ String.valueOf(each.getCharge()) + "\n";
		}
		result += "Amount owed is " + String.valueOf(this.getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(this.getTotalFrequentRenterPoints())
				+ " frequent renter points";
		
		return result;
	}
	
	/**
	 * 迭代计算租赁的所有影片的积分
	 * @return
	 */
	private int getTotalFrequentRenterPoints(){
		int result = 0;
		Enumeration<Rental_Refacoting03> elements = this.getRentals().elements();
		while (elements.hasMoreElements()) {
			Rental_Refacoting03 aRental = (Rental_Refacoting03) elements.nextElement();
			result += aRental.getFrequentRenterPoints();
		}
		return result;
	}
	
	/**
	 * 迭代计算租赁的所有影片的总费用
	 * @return
	 */
	private double getTotalCharge(){
		double result = 0;
		Enumeration<Rental_Refacoting03> elements = this.getRentals().elements();
		while (elements.hasMoreElements()) {
			Rental_Refacoting03 aRental = (Rental_Refacoting03) elements.nextElement();
			result += aRental.getCharge();
		}
		return result;
	}
}
