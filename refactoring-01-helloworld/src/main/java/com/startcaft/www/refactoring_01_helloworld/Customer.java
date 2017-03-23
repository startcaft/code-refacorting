package com.startcaft.www.refactoring_01_helloworld;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 客户类;
 * 包含一个Rental集合的成员变量;
 * statement()用于计算客户的费用和积分;
 */
public class Customer {
	
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
	
	public Customer(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * 模拟打印详单的函数
	 * @return
	 */
	public String statement(){
		
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		
		Enumeration<Rental> rentalElements = rentals.elements();
		String result = "Rental Record for [" + getName() + "]\n";
		
		while (rentalElements.hasMoreElements()) {
			
			//计算租金
			double currentAmount = 0;
			Rental each = (Rental) rentalElements.nextElement();
			
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				currentAmount += 2;
				if(each.getDaysRented() > 2){
					currentAmount += (each.getDaysRented() - 2) * 1.5;
				}
				break;
			case Movie.NEW_RELEASE:
				currentAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				currentAmount += 1.5;
				if(each.getDaysRented() > 3){
					currentAmount += (each.getDaysRented() - 3) * 1.5;
				}
				break;
			}
			
			//计算客户积分，新片有额外的加成。
			frequentRenterPoints += 1;
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
					each.getDaysRented() > 1) {
				frequentRenterPoints++;
			}
			
			result += "\t<<" + each.getMovie().getTitle() + ">>\t"
					+ String.valueOf(currentAmount) + "\n";
			
			totalAmount += currentAmount;
		}
		
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		
		return result;
	}
}
