package com.refacotring.prices;

import com.startcaft.www.refactoring_01_helloworld.Movie;

/**
 * 新上映影片计价类
 */
public class NewReleasePrice extends Price {

	@Override
	public int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

	@Override
	public double getCharge(int daysRented) {
		return daysRented * 3;
	}
	
	@Override
	public int getFrequentRenterPoints(int daysRented) {
		{
			return (daysRented > 1) ? 2 : 1;
		}
	}
}
