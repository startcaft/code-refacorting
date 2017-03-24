package com.oodp.factorys;

import com.oodp.products.AbstractProductA;
import com.oodp.products.AbstractProductB;
import com.oodp.products.ProductA1;
import com.oodp.products.ProductB1;

public class ConcrateFactory1 implements AbstractFactory {

	@Override
	public AbstractProductA createProductA() {
		return new ProductA1();
	}

	@Override
	public AbstractProductB createProductB() {
		return new ProductB1();
	}

}
