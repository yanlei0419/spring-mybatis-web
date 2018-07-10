package org.seeker.common.util;

import java.util.UUID;

public class Uuid {
	private static class SingleUuid{
		private static Uuid single=new Uuid();
	}
	private Uuid(){}
	private static Uuid getInstance(){
		return SingleUuid.single;
	}
	
	private String getId(){
		return UUID.randomUUID().toString();
	}

	public static String getUUID(){
		return Uuid.getInstance().getId();
	}
	public static String getUUIDNotSign(){
		return Uuid.getInstance().getId().replaceAll("-","");
	}

}
