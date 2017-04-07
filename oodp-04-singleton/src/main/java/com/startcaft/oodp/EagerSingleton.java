package com.startcaft.oodp;

/**
 * 饿汉式单例类，类加载时构建单例类的实例
 */
public class EagerSingleton {
	
	private static EagerSingleton instance = new EagerSingleton();
	
	private EagerSingleton(){}
	
	public static EagerSingleton getInstance(){
		return instance;
	}
}
