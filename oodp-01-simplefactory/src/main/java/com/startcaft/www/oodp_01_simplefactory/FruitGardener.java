package com.startcaft.www.oodp_01_simplefactory;


/**
 * 园丁类(简单工厂中的工厂类)
 */
public class FruitGardener {
	
	/**
	 * 静态工厂方法
	 */
	public static Fruit factory(String which)
		throws BadFruiException {
		{
			if (which.equalsIgnoreCase("appel")) {
				return new Apple();
			}
			else if (which.equalsIgnoreCase("strawberry")) {
				return new Strawberry();
			}
			else if (which.equalsIgnoreCase("grape")) {
				return new Grape();
			}
			else{
				throw new BadFruiException("Bad fruit request");
			}
		}
	}
}
