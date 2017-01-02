package source.refacorting01;

import java.util.Enumeration;
import java.util.Vector;

import souces.Movie;
import souces.Rental;

public class Customer_Recactoring01 {
	
	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Rental> getRentals() {
		return rentals;
	}
	public void setRentals(Vector<Rental> rentals) {
		this.rentals = rentals;
	}
	
	public Customer_Recactoring01(String name) {
		super();
		this.name = name;
	}
	
	public String statement(){
		double totalAmount = 0;
		int frequetRenterPoints = 0;
		String result = "Rental Record for [" + getName() + "]\n";
		
		Enumeration<Rental> rentalElements = rentals.elements();
		while (rentalElements.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentalElements.nextElement();
			
			//费用
			thisAmount = this.amountFor(each);
			//积分
			frequetRenterPoints += this.getFrequentRenterPoints(each);
			
			result += "\t<<" + each.getMovie().getTitle() + ">>\t"
					+ String.valueOf(thisAmount) + "\n";
			
			totalAmount += thisAmount;
		}
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequetRenterPoints)
				+ " frequent renter points";
		
		return result;
	}
	
	/**
	 * 提炼出计算常客积分的方法
	 * @return
	 */
	private int getFrequentRenterPoints(Rental arental){
		if (arental.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
				arental.getDaysRented() > 1) {
			return 2;
		}
		return 1;
	}
	
	/**
	 * 提炼出计算顾客费用的方法
	 * @param each
	 * @return
	 */
	private double amountFor(Rental arental){
		double result = 0;
		
		switch (arental.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			result += 2;
			if(arental.getDaysRented() > 2){
				result += (arental.getDaysRented() - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			result += arental.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			result += 1.5;
			if(arental.getDaysRented() > 3){
				result += (arental.getDaysRented() - 3) * 1.5;
			}
			break;
		}
		
		return result;
	}
}
