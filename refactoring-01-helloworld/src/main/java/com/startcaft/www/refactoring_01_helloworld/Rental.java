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
	
	//////////////////////////////////////////////
	
	/**
	 * 计算租赁影片的费用
	 */
	double getCharge(){
		{
			double result = 0;
			{
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
			}
			return result;
		}
	}
	
	/**
	 * 计算租赁影片获得的积分，新上映的影片并且租赁天数大于1天的积2分，其余积1分
	 */
	int getFrequentRenterPoints(){
		{
			if((this.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& this.getDaysRented() > 1 )
				return 2;
			else
				return 1;
		}
	}
}
