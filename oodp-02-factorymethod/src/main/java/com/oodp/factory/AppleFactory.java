package com.oodp.factory;

import com.oodp.product.Apple;
import com.oodp.product.Fruit;

public class AppleFactory implements FruitFactory {

	@Override
	public Fruit factory() {
		return new Apple();
	}

}
