package source.refacotring03;

public class Movie_Refacorting03 {
	
	public static final int CHILDRENS = 2;//儿童片
	public static final int REGULAR = 0;//普通片
	public static final int NEW_RELEASE = 1;//新片
	
	private String title;
	private Price price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public Movie_Refacorting03(String title, Price price) {
		super();
		this.title = title;
		this.price = price;
	}
	
	/**
	 * 获取影片的类型价格
	 * @return
	 */
	public int getPriceCode(){
		return price.getPriceCode();
	}
	
	/**
	 * 设置影片的类型价格，根据arg的不同返回不同的Price抽象类的具体实现类。
	 * @param arg
	 */
	public void setPriceCode(int arg){
		switch (arg) {
		case REGULAR:
			this.price = new RegularPrice();
			break;
		case CHILDRENS:
			this.price = new ChildrensPrice();
			break;
		case NEW_RELEASE:
			this.price = new NewReleasePrice();
			break;
		default:
			throw new IllegalArgumentException("错误的price code");
		}
	}
	
	/**
	 * 计算租赁影片的积分
	 * @param daysRented
	 * @return
	 */
	int getFerquentRenterPoints(int daysRented){
		return price.getFerquentRenterPoints(daysRented);
	}
	
	/**
	 * 计算租赁影片的费用
	 * @param daysRented 租赁天数
	 * @return
	 */
	double getCharge(int daysRented){
		return price.getCharge(daysRented);
	}
}
