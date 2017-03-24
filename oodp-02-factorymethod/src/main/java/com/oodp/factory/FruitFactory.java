package com.oodp.factory;

import com.oodp.product.Fruit;

/**
 * 抽象工厂
 */
public interface FruitFactory {
	
	/**
	 * 工厂方法
	 */
	public Fruit factory();
}
