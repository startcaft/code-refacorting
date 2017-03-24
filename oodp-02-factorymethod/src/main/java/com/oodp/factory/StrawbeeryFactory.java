package com.oodp.factory;

import com.oodp.product.Fruit;
import com.oodp.product.Strawberry;

public class StrawbeeryFactory implements FruitFactory {

	@Override
	public Fruit factory() {
		return new Strawberry();
	}
}
