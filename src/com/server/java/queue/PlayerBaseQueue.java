package com.server.java.queue;

import com.server.java.entity.MsgEntity;


public class PlayerBaseQueue extends BaseQueue<MsgEntity> {
	private static final PlayerBaseQueue INSTANCE = new PlayerBaseQueue();

	private PlayerBaseQueue() {
		
	}

	public static PlayerBaseQueue getInstance() {
		return INSTANCE;
	}

}
