package com.refacotring.prices;

import com.startcaft.www.refactoring_01_helloworld.Movie;

/**
 * 儿童片计价类
 */
public class ChildrensPrice extends Price {

	@Override
	public int getPriceCode() {
		return Movie.CHILDRENS;
	}

	@Override
	public double getCharge(int daysRented) {
		{
			double result = 0;
			if(daysRented > 3){
				result += (daysRented - 3) * 1.5;
			}
			return result;
		}
	}

}
