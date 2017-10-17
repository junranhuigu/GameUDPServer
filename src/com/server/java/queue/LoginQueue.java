package com.server.java.queue;

import com.server.java.entity.MsgEntity;

public class LoginQueue extends BaseQueue<MsgEntity> {
	// private BaseQueue<MsgEntity> baseQueue = new BaseQueue<MsgEntity>();
	private static final LoginQueue INSTANCE = new LoginQueue();

	private LoginQueue() {
	}

	public static LoginQueue getInstance() {
		return INSTANCE;
	}

}
