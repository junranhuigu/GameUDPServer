package com.server.java;

import java.util.List;

import org.slf4j.Logger;

import com.server.java.entity.MsgEntity;
import com.server.java.log.LoggerProvider;
/**
 * 交互处理类接口
 * */
public abstract class CmdHandler {
	protected static Logger logger = LoggerProvider.getLogger(CmdHandler.class);
	
	/**
	 * 处理方法
	 * @param msgEntity 消息
	 * @param commandList 要发送的消息集合 直接放入即可
	 * */
	public abstract void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList);
}
