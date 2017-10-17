package com.server.java.entity;

import java.io.Serializable;

import io.netty.channel.Channel;

public class MsgEntity implements Serializable {

	private static final long serialVersionUID = 7457164738542268945L;
	private short cmdCode;// 储存命令码
	private byte[] data;// 存放实际数据,用于protobuf解码成对应message
	private Channel channel;// 当前玩家的channel
	private long receiveTime;
	
	public MsgEntity() {
		this.receiveTime = System.currentTimeMillis();
	}

	public short getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(short cmdCode) {
		this.cmdCode = cmdCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void autohandleMsg(MsgEntity msgEntity) {
		System.out.println("aotosend msg code = " + msgEntity.getCmdCode());
		msgEntity.getChannel().writeAndFlush(msgEntity.getData());
	}

	public long getReceiveTime() {
		return receiveTime;
	}

}
