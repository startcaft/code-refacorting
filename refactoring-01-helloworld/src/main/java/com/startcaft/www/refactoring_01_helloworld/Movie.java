package com.startcaft.www.refactoring_01_helloworld;

import com.refacotring.prices.ChildrensPrice;
import com.refacotring.prices.NewReleasePrice;
import com.refacotring.prices.Price;
import com.refacotring.prices.RegularPrice;

/**
 * 影片;
 * 包含三个静态变量来标识影片类型;
 */
public class Movie {
	
	public static final int CHILDRENS = 2;//儿童片
	public static final int REGULAR = 0;//普通片
	public static final int NEW_RELEASE = 1;//新片
	
	private String title;
	private Price _price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取Price的价格代号
	 */
	public int getPriceCode() {
		return _price.getPriceCode();
	}
	/**
	 * 根据价格代码设置具体的Price实现类
	 */
	public void setPriceCode(int arg) {
		switch (arg) {
			case REGULAR:
				_price = new RegularPrice();
				break;
			case CHILDRENS:
				_price = new ChildrensPrice();
				break;
			case NEW_RELEASE:
				_price = new NewReleasePrice();
				break;
			default:
				throw new IllegalArgumentException("Incorretc Price Code");
		}
	}
	
	public Movie(String title, int priceCode) {
		super();
		this.title = title;
		setPriceCode(priceCode);
	}
	
	
	/////////////////////////////////////////////
	
	/**
	 * 根据租期来计算费用。
	 * @param daysRented 租期，该属性来自于Rental类
	 */
	double getCharge(int daysRented){
		return _price.getCharge(daysRented);
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
