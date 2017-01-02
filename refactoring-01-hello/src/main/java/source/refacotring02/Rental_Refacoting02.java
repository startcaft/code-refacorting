package source.refacotring02;

import souces.Movie;

public class Rental_Refacoting02 {
	
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
	
	public Rental_Refacoting02(Movie movie, int daysRented) {
		super();
		this.movie = movie;
		this.daysRented = daysRented;
	}
	
	/**
	 * 封装的计算顾客积分的方法
	 * @return
	 */
	int getFrequentRenterPoints(){
		if (this.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
				this.getDaysRented() > 1) {
			return 2;
		}
		return 1;
	}
	
	/**
	 * 封装的计算顾客总费用的方法
	 * @return
	 */
	double getCharge(){
		double result = 0;
		
		switch (this.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			result += 2;
			if(this.getDaysRented() > 2){
				result += (this.getDaysRented() - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			result += this.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			result += 1.5;
			if(this.getDaysRented() > 3){
				result += (this.getDaysRented() - 3) * 1.5;
			}
			break;
		}
		
		return result;
	}
}
