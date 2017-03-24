package com.oodp.factorys;

import com.oodp.products.AbstractProductA;
import com.oodp.products.AbstractProductB;
import com.oodp.products.ProductA2;
import com.oodp.products.ProductB2;

public class ConcreteFactory2 implements AbstractFactory {

	@Override
	public AbstractProductA createProductA() {
		return new ProductA2();
	}

	@Override
	public AbstractProductB createProductB() {
		return new ProductB2();
	}

}
