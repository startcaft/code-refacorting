package com.startcaft.www.refactoring_01_helloworld;

/**
 * 租赁;
 * 包含一个Movie类的成员变量;
 */
public class Rental {
	
	private Movie movie;
	private int daysRented;
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}
	
	public Rental(Movie movie, int daysRented) {
		super();
		this.movie = movie;
		this.daysRented = daysRented;
	}
	
}
