package com.server.java.netty;

import com.server.java.log.LoggerProvider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer implements Runnable {
	private int port;

	public NettyServer(int port) {
		super();
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void run() {
		try {
			EventLoopGroup group = new NioEventLoopGroup();
			try {
				Bootstrap b = new Bootstrap();
				b.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, false)//支持广播
					.handler(new LoggingHandler(LogLevel.INFO)).handler(new NettyServerInitializer());
				
				ChannelFuture f = b.bind(port).sync();
				LoggerProvider.getLogger(NettyServer.class).info("服务器启动，端口" + port + "开放");
				f.channel().closeFuture().sync();
			} finally {
				group.shutdownGracefully();
			}
		} catch (InterruptedException e) {
			LoggerProvider.getLogger(NettyServer.class).error("服务器启动，端口" + port + "开放失败", e);
		}
	}

}
