package com.server.java.util;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.google.protobuf.GeneratedMessage;
import com.server.java.entity.MsgEntity;

class ClientHandler extends ChannelHandlerAdapter{
	private GeneratedMessage req;
	private short code;
	
	public ClientHandler(GeneratedMessage req, short code) {
		this.req = req;
		this.code = code;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		MsgEntity entity = new MsgEntity();
		
		entity.setCmdCode(code);
		entity.setData(req.toByteArray());
		
		ctx.writeAndFlush(entity);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("连接断开");
	}
}
