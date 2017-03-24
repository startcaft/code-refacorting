package com.oodp.product;


public class Grape implements Fruit {
	
	private boolean seedless;//是否无籽
	public boolean isSeedless() {
		return seedless;
	}
	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}
	
	/*
	 * 辅助方法
	 */
	public static void log(String msg){
		System.out.println(msg);
	}

	@Override
	public void grow() {
		log("葡萄正在生长...");
	}

	@Override
	public void harvest() {
		log("葡萄已经收获");
	}

	@Override
	public void plant() {
		log("葡萄已经种植");
	}

}
