package com.oodp.observer;

import java.util.Enumeration;
import java.util.Vector;

public abstract class Subject {
	
	//这个聚集保存了所有对观察者对象的引用
	private Vector<Observer> observers = new Vector<Observer>();
	
	/**
	 * 登记一个新的观察者
	 */
	public void attch(Observer observer) {
		observers.add(observer);
		System.out.println("Atteched an observer");
	}
	
	/**
	 * 删除一个已经登记过的观察者
	 */
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	/**
	 * 通知所有登记过的观察者
	 */
	public void notifyObservers() {
		Enumeration<Observer> enumeration = this.observers();
		while(enumeration.hasMoreElements()){
			enumeration.nextElement().update();
		}
	}
	
	/**
	 * 获取观察者集合的枚举,
	 * 返回的Enumeration<Observer> 只是一个拷贝，从而使得外界不能修改。
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<Observer> observers(){
		return ((Vector<Observer>)observers.clone()).elements();
	}
}
