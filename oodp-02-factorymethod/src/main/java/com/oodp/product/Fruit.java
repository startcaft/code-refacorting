package com.oodp.product;

/**
 * 水果接口(简单工厂中的产品类)
 */
public interface Fruit {
	
	/**
	 * 生长
	 */
	void grow();
	
	/**
	 * 收获
	 */
	void harvest();
	
	/**
	 * 种植
	 */
	void plant();
}
