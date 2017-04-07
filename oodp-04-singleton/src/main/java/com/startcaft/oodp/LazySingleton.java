package com.startcaft.oodp;

/**
 * 懒汉式单例类，第一次调用静态工厂方法时构建单例类实例
 */
public class LazySingleton {
	private static LazySingleton instance = null;
	
	private LazySingleton(){}
	
 	synchronized public static LazySingleton getInstance(){
 		{
 			if (instance == null) {
 				instance = new LazySingleton();
			}
 			return instance;
 		}
	}
}
