package source.refacotring03;

import souces.Movie;

/**
 * 普通影片类型
 * @author Administrator
 */
public class RegularPrice extends Price {

	@Override
	int getPriceCode() {
		return Movie.REGULAR;
	}

	@Override
	double getCharge(int daysRented) {
		double result = 0;
		if(daysRented > 2){
			result += (daysRented - 2) * 1.5;
		}
		return result;
	}

	@Override
	int getFerquentRenterPoints(int daysRented) {
		return 1;
	}
}
