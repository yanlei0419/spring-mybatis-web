package org.springboot.config.single;

/**
 * @author ve
 *第七种（双重校验锁）
 * 这个是第二种方式的升级版，俗称双重检查锁定，详细介绍请查看：http://www.ibm.com/developerworks/cn/java/j-dcl.html
在JDK1.5之后，双重检查锁定才能够正常达到单例效果。


有两个问题需要注意：
1.如果单例由不同的类装载器装入，那便有可能存在多个单例类的实例。假定不是远端存取，例如一些servlet容器对每个servlet使用完全不同的类装载器，这样的话如果有两个servlet访问一个单例类，它们就都会有各自的实例。
2.如果Singleton实现了java.io.Serializable接口，那么这个类的实例就可能被序列化和复原。不管怎样，如果你序列化一个单例类的对象，接下来复原多个那个对象，那你就会有多个单例类的实例。
对第一个问题修复的办法是
 */
public class Singleton07 {
	private volatile static Singleton07 singleton;

	private Singleton07() {
	}

	public static Singleton07 getSingleton() {
		if (singleton == null) {
			synchronized (Singleton07.class) {
				if (singleton == null) {
					singleton = new Singleton07();
				}
			}
		}
		return singleton;
	}
}