package com.refacotring.prices;


/**
 * 抽象计价类
 */
public abstract class Price {
	
	/**
	 * 返回价格代码
	 */
	public abstract int getPriceCode();
	
	/**
	 * 根据租期计费
	 */
	public abstract double getCharge(int daysRented);
	
	/**
	 * 默认的积分方法
	 */
	public int getFrequentRenterPoints(int daysRented){
		return 1;
	}
}
