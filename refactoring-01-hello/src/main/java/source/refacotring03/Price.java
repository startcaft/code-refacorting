package source.refacotring03;

/**
 * 把影片的类型价格抽象出来，不能让Movie有修改Price的机会
 * @author Administrator
 */
public abstract class Price {
	
	/**
	 * 获取类型影片的价格
	 * @return
	 */
	abstract int getPriceCode();
	
	/**
	 * 计算租赁某一个影片的费用
	 * @param daysRented 租赁天数
	 * @return
	 */
	abstract double getCharge(int daysRented);
	
	/**
	 * 计算租赁某一个影片所获取的积分
	 * @param daysRented 租赁天数
	 * @return
	 */
	abstract int getFerquentRenterPoints(int daysRented);
}
