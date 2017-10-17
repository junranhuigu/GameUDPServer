package com.server.java.constants;

import com.server.java.handler.LoginHandler;
import com.server.java.queue.GameMsgConfig;

/**
 * 详细操作编号
 * */
public class CmdConstant {
	/**
	 * 登陆
	 * */
	@GameMsgConfig(handleClass = LoginHandler.class, handleMethod = "login")
	public static final short LOGIN = 17000;
	
}
