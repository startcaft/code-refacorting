package com.oodp.factory;

import com.oodp.product.Fruit;
import com.oodp.product.Grape;

public class GrapeFactory implements FruitFactory {

	@Override
	public Fruit factory() {
		return new Grape();
	}

}
