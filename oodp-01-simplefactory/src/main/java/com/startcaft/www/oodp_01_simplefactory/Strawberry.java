package com.startcaft.www.oodp_01_simplefactory;

public class Strawberry implements Fruit {
	
	/*
	 * 辅助方法
	 */
	public static void log(String msg){
		System.out.println(msg);
	}
	
	@Override
	public void grow() {
		log("草莓正在生长...");
	}

	@Override
	public void harvest() {
		log("草莓已经收获");
	}

	@Override
	public void plant() {
		log("草莓已经种植");
	}

}
