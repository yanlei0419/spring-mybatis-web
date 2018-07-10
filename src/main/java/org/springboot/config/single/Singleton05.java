package org.springboot.config.single;

import java.util.UUID;

public class Singleton05 {
	private static class SingleUuid{
		private static Singleton05 single=new Singleton05();
	}
	private Singleton05(){}
	private static Singleton05 getInstance(){
		return SingleUuid.single;
	}
	
	private String getId(){
		return UUID.randomUUID().toString();
	}

	public static synchronized String getUUID(){
		return Singleton05.getInstance().getId();
	}

}
