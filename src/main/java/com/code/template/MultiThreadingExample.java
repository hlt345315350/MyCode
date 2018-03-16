package com.code.template;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 涉及到多线程，先创建一个资源类
 * 线程	操作	资源类
 * 高内聚	低耦合
 * 线程调用
 * @author Administrator
 *
 */

class Ticket {

	private int number = 30;
	private Lock lock = new ReentrantLock();

	public void sale() {
		lock.lock();
		try {

			if (number > 0) {
				System.out.println("这是" + Thread.currentThread().getName() + "卖出的票,还剩" + (--number) + "张票");
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}
	}
}

public class MultiThreadingExample {

	public static void main(String[] args) {
		Ticket ticket = new Ticket();

		//Lambda表达式简写
		new Thread(() -> {for (int i = 1; i <= 40; i++) {ticket.sale();}},"AA").start();
		new Thread(() -> {for (int i = 1; i <= 40; i++) {ticket.sale();}},"BB").start();
		new Thread(() -> {for (int i = 1; i <= 40; i++) {ticket.sale();}},"CC").start();
		
		
		/*new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 40; i++) {
					ticket.sale();
				}
			}
		}, "AA").start();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 40; i++) {
					ticket.sale();
				}
			}
		}, "BB").start();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 40; i++) {
					ticket.sale();
				}
			}
		}, "CC").start();*/
	}

}
