package org.springboot.config.single;

/**
 * @author ve 第二种（懒汉，线程安全）： 这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，
 *         但是，遗憾的是，效率很低，99%情况下不需要同步。
 */
public class Singleton02 {
	private static Singleton02 instance;

	private Singleton02() {
	}

	public static synchronized Singleton02 getInstance() {
		if (instance == null) {
			instance = new Singleton02();
		}
		return instance;
	}
}