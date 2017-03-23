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
	 */
	public String statement(){
		{
			Enumeration<Rental> rentalElements = rentals.elements();
			String result = "Rental Record for [" + getName() + "]\n";
			
			while (rentalElements.hasMoreElements()) {
				Rental each = (Rental) rentalElements.nextElement();
				result += "\t<<" + each.getMovie().getTitle() + ">>\t"
						+ String.valueOf(each.getCharge()) + "\n";
			}
			
			//获取总费用和总积分
			result += "Amount owed is " + String.valueOf(this.getTotalCharge()) + "\n";
			result += "You earned " + String.valueOf(this.getTotalFrequentRenterPoints())
					+ " frequent renter points";
			return result;
		}
	}
	
	/**
	 * 迭代Enumeration<Rental>集合，获取总的租赁费用
	 */
	private double getTotalCharge(){
		{
			double result = 0;
			Enumeration<Rental> rentalList = rentals.elements();
			while (rentalList.hasMoreElements()) {
				Rental rental = (Rental) rentalList.nextElement();
				result += rental.getCharge();
			}
			
			return result;
		}
	}
	
	/**
	 * 迭代Enumeration<Rental>集合，获取总的积分
	 */
	private int getTotalFrequentRenterPoints(){
		{
			int result = 0;
			Enumeration<Rental> rentalList = rentals.elements();
			while (rentalList.hasMoreElements()) {
				Rental rental = (Rental) rentalList.nextElement();
				result += rental.getFrequentRenterPoints();
			}
			
			return result;
		}
	}
}
