package com.server.java.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;

public class NettyServerInitializer extends ChannelInitializer<DatagramChannel> {

	@Override
	protected void initChannel(DatagramChannel channel) throws Exception {
		channel.pipeline().addLast("decoder", new NettyMsgDecoder())// 解码器
		.addLast("encoder", new NettyMsgEncoder())// 编码器
		.addLast("handler", new ServerHanlder());
	}

//	@Override
//	protected void initChannel(SocketChannel ch) throws Exception {
//		ch.pipeline().addLast("decoder", new NettyMsgDecoder())// 解码器
//				.addLast("encoder", new NettyMsgEncoder())// 编码器
//				.addLast("handler", new ServerHanlder());
//	}

}
