package source.refacotring03;

/**
 * 儿童片类型
 * @author Administrator
 */
public class ChildrensPrice extends Price {

	@Override
	int getPriceCode() {
		return Movie_Refacorting03.CHILDRENS;
	}

	@Override
	double getCharge(int daysRented) {
		double result = 0;
		if(daysRented > 3){
			result += (daysRented - 3) * 1.5;
		}
		return result;
	}

	@Override
	int getFerquentRenterPoints(int daysRented) {
		return 1;
	}

}
