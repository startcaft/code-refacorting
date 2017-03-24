package com.oodp.factorys;

import com.oodp.products.AbstractProductA;
import com.oodp.products.AbstractProductB;

//抽象工厂角色
public interface AbstractFactory {
	
	AbstractProductA createProductA();
	AbstractProductB createProductB();
}
