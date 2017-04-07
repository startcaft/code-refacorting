package com.oodp.observer;


public class ConcreteSubject extends Subject {
	
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 调用这个方法更改主题的状态
	 */
	public void change(String newState){
		setState(newState);
		this.notifyObservers();
	}
}
