package com.startcaft.www.oodp_02_factorymethod;

import org.junit.Test;
import com.oodp.factory.AppleFactory;
import com.oodp.factory.FruitFactory;
import com.oodp.product.Fruit;

public class TestFactoryMethod {
	
	@Test
	public void test(){
		{
			FruitFactory factory = new AppleFactory();
			
			Fruit fruit = factory.factory();
			fruit.grow();
		}
	}
}	
