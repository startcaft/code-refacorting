package main;

import com.oodp.observer.ConcreteObserver;
import com.oodp.observer.ConcreteSubject;
import com.oodp.observer.Observer;

public class Client {
	
	private static ConcreteSubject subject;
	private static Observer observer;
	
	public static void main(String[] args) {
		
		//创建主题对象
		subject = new ConcreteSubject();
		//创建观察者对象
		observer = new ConcreteObserver();
		//将观察者对象登记到主题对象上
		subject.attch(observer);
		
		//改变主题对象的状态
		subject.change("new state");
	}

}
