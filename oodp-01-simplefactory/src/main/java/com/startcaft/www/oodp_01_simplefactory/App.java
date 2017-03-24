package com.startcaft.www.oodp_01_simplefactory;

public class App {

	public static void main(String[] args) {
		
		try {
			Fruit fruit = FruitGardener.factory("appel");
			fruit.grow();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
