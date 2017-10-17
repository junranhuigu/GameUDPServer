package com.server.java.cache;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import com.server.java.log.LoggerProvider;

public class ServerCache {
	private static ServerCache sc;
	private static Logger logger = LoggerProvider.getLogger(ServerCache.class);
	
	private ConcurrentHashMap<Channel, String> CHANNEL_PLAYER;
	private ConcurrentHashMap<String, Channel> ID_PLAYER;
//	private ConcurrentHashMap<Integer, Gate> gates;//分线
	
	private ServerCache() {
		CHANNEL_PLAYER = new ConcurrentHashMap<>();
		ID_PLAYER = new ConcurrentHashMap<>();
	}

	public static ServerCache getInstance() {
		if(sc == null){
			sc = new ServerCache();
		}
		return sc;
	}

	/**
	 * 登陆
	 * @return 分线id
	 * */
	public void login(Channel ch, String id) {
		if (ID_PLAYER.get(id) != null) {//重新连接 关闭之前的连接线路
//			logout(ID_PLAYER.get(id), id);
			Channel old = ID_PLAYER.remove(id);
			if(old != null && old.isOpen()){
				old.close();
			}
		}
		CHANNEL_PLAYER.put(ch, id);
		ID_PLAYER.put(id, ch);
		logger.info(id + "登陆游戏，当前在线人数：" + ID_PLAYER.size());
	}

	/**
	 * 登出
	 * */
	public void logout(Channel ch, String id) {
		logger.info(id + "下线了，当前在线人数：" + ID_PLAYER.size());
		if (id != null) {
			Channel channel = ID_PLAYER.remove(id);
			if(ch == null && channel != null){
				ch = channel;
			}
		}
		if (ch != null) {
			ch.close();
			CHANNEL_PLAYER.remove(ch);
		}
	}
	
	public String getId(Channel channel){
		return CHANNEL_PLAYER.get(channel);
	}
	public Channel getChannel(String id){
		return ID_PLAYER.get(id);
	}
	
}
