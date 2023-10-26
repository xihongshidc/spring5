package com.dc.springTest.event;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * Description: java 事件监听模型
 * Author: duancong
 * Date: 2023/10/26 16:31
 */
public class ObservableDemo {
	public static void main(String[] args) {
		Observable observable = new ObservalbeEvent();//被观察对象

		//添加观察者 (监听者)
		observable.addObserver(new ObserverOne());
		EventObject eventObject = new EventObject("Hello world !");

		observable.notifyObservers(eventObject);
	}

	static class ObservalbeEvent extends Observable{
		@Override
		public synchronized void setChanged() {
			super.setChanged();
		}

		@Override
		public void notifyObservers(Object arg) {
			setChanged();
			super.notifyObservers(arg);
			clearChanged();
		}
	}

	static class ObserverOne implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			EventObject eventObject =(EventObject) arg;
			System.out.println("接收到的参数 : " + eventObject);
		}
	}
}
