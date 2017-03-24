package com.startcaft.www.refactoring_01_helloworld;

/**
 * 影片;
 * 包含三个静态变量来标识影片类型;
 */
public class Movie {
	
	public static final int CHILDRENS = 2;//儿童片
	public static final int REGULAR = 0;//普通片
	public static final int NEW_RELEASE = 1;//新片
	
	private String title;
	private int priceCode;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}
	
	public Movie(String title, int priceCode) {
		super();
		this.title = title;
		this.priceCode = priceCode;
	}
	
	
	/////////////////////////////////////////////
	
	/**
	 * 根据租期来计算费用。
	 * @param daysRented 租期，该属性来自于Rental类
	 */
	double getCharge(int daysRented){
		{
			double result = 0;
			{
				switch (this.getPriceCode()) {
					case Movie.REGULAR:
						result += 2;
						if(daysRented > 2){
							result += (daysRented - 2) * 1.5;
						}
						break;
					case Movie.NEW_RELEASE:
						result += daysRented * 3;
						break;
					case Movie.CHILDRENS:
						result += 1.5;
						if(daysRented > 3){
							result += (daysRented - 3) * 1.5;
						}
						break;
				}
			}
			return result;
		}
	}
	
	/**
	 * 新上映的影片并且租赁天数大于1天的积2分，其余积1分。
	 * @param daysRented 租期，该属性来自于Rental类
	 */
	int getFrequentRenterPoints(int daysRented){
		{
			if((this.getPriceCode() == Movie.NEW_RELEASE)
					&& daysRented > 1 )
				return 2;
			else
				return 1;
		}
	}
}
