package org.springboot.config.single;

/**
 * @author ve 第一种（懒汉，线程不安全）： 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
 */
public class Singleton01 {
	private static Singleton01 instance;

	private Singleton01() {
	}

	public static Singleton01 getInstance() {
		if (instance == null) {
			instance = new Singleton01();
		}
		return instance;
	}
}