package com.startcaft.www.oodp_01_simplefactory;

public class Apple implements Fruit {
	
	private int treeAge;//树龄
	public int getTreeAge() {
		return treeAge;
	}
	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}
	
	/*
	 * 辅助方法
	 */
	public static void log(String msg){
		System.out.println(msg);
	}
	
	@Override
	public void grow() {
		log("苹果树正在生长...");
	}

	@Override
	public void harvest() {
		log("苹果已经收获");
	}

	@Override
	public void plant() {
		log("苹果树苗已经种植");
	}

}
