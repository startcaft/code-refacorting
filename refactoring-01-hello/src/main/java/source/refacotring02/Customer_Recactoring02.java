package source.refacotring02;

import java.util.Enumeration;
import java.util.Vector;

public class Customer_Recactoring02 {
	
	private String name;
	private Vector<Rental_Refacoting02> rentals = new Vector<Rental_Refacoting02>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Rental_Refacoting02> getRentals() {
		return rentals;
	}
	public void setRentals(Vector<Rental_Refacoting02> rentals) {
		this.rentals = rentals;
	}
	
	public Customer_Recactoring02(String name) {
		super();
		this.name = name;
	}
	
	public String statement(){
		String result = "Rental Record for [" + getName() + "]\n";
		Enumeration<Rental_Refacoting02> rentalElements = rentals.elements();
		
		while (rentalElements.hasMoreElements()) {
			Rental_Refacoting02 each = (Rental_Refacoting02) rentalElements.nextElement();
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
		Enumeration<Rental_Refacoting02> elements = this.getRentals().elements();
		while (elements.hasMoreElements()) {
			Rental_Refacoting02 aRental = (Rental_Refacoting02) elements.nextElement();
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
		Enumeration<Rental_Refacoting02> elements = this.getRentals().elements();
		while (elements.hasMoreElements()) {
			Rental_Refacoting02 aRental = (Rental_Refacoting02) elements.nextElement();
			result += aRental.getCharge();
		}
		return result;
	}
	
//	public String htmlStatement(){
//		String result = "<H1>Rental Record for <em>[" + getName() + "]</em></h1>\n";
//		Enumeration<Rental_Refacoting02> rentalElements = rentals.elements();
//		while (rentalElements.hasMoreElements()) {
//			Rental_Refacoting02 each = (Rental_Refacoting02) rentalElements.nextElement();
//			result += "<<" + each.getMovie().getTitle() + ">>:"
//					+ String.valueOf(this.geTotalCharge()) + "<br/>\n";
//		}
//		result += "<p>Amount owed is <em>" + String.valueOf(this.geTotalCharge()) + "<em><p>\n";
//		result += "<p>You earned <em>" + String.valueOf(this.getTotalFrequentRenterPoints())
//				+ "<em> frequent renter points</p>";
//		
//		return result;
//	}
}
