package com.server.java.queue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.server.java.CmdHandler;

/**
 * 用于指定 服务器与客户端协议 处理方法的消息类型
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GameMsgConfig {
	/**
	 * 游戏消息处理的类
	 * */
	public Class<? extends CmdHandler> handleClass();
	/**
	 * 游戏消息处理的方法名
	 * */
	public String handleMethod();
}
