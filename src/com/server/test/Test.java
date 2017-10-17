package com.server.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;


public class Test {

	public static void main(String args[]) throws Exception{
		Bootstrap b = new Bootstrap();  
		EventLoopGroup group = new NioEventLoopGroup();
		b.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST,true)//支持广播
			.handler(new SimpleChannelInboundHandler<DatagramPacket>() {
			@Override
			protected void messageReceived(ChannelHandlerContext ctx,
					DatagramPacket packet) throws Exception {
				// 读取收到的数据
                String body = packet.content().toString(CharsetUtil.UTF_8);
                System.out.println("【NOTE】>>>>>> 收到" + packet.sender() + "客户端的数据：" + body); 
                 
                // 回复一条信息给客户端
                ctx.writeAndFlush(new DatagramPacket(
                Unpooled.copiedBuffer("Hello，我是Server，我的时间戳是"+System.currentTimeMillis() + " 1"
                                , CharsetUtil.UTF_8)
                                , packet.sender()));
                ctx.writeAndFlush(new DatagramPacket(
                		Unpooled.copiedBuffer("Hello，我是Server，我的时间戳是"+System.currentTimeMillis() + " 2"
                				, CharsetUtil.UTF_8)
                				, packet.sender()));
			}
		});
		System.out.println("UDP服务器启动");
		b.bind(10086).sync().channel().closeFuture().await();
		System.out.println("UDP服务器关闭");
	}
}
