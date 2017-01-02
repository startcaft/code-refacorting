package source.refacotring03;

/**
 * 新片类型
 * @author Administrator
 *
 */
public class NewReleasePrice extends Price {

	@Override
	int getPriceCode() {
		return Movie_Refacorting03.NEW_RELEASE;
	}

	@Override
	double getCharge(int daysRented) {
		double result = 0;
		result += daysRented * 3;
		return result;
	}

	@Override
	int getFerquentRenterPoints(int daysRented) {
		return (daysRented > 1) ? 2 : 1;
	}
}
