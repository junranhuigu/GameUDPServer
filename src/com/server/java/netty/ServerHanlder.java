package com.server.java.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;

import com.server.java.cache.ServerCache;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;
import com.server.java.log.LoggerProvider;
import com.server.java.queue.LoginQueue;
import com.server.java.queue.PlayerBaseQueue;

@Sharable
public class ServerHanlder extends SimpleChannelInboundHandler<MsgEntity> {
	private static Logger logger = LoggerProvider
			.getLogger(ServerHanlder.class);

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, MsgEntity msg)
			throws Exception {
		if (msg == null) {
			return;
		}
		msg.setChannel(ctx.channel());
		
		short csCommondCode = msg.getCmdCode();
		if(csCommondCode == CmdConstant.LOGIN){
			LoginQueue.getInstance().put(msg);
		} else if (csCommondCode >= 11000 && csCommondCode < 19000) {//合法协议号
			PlayerBaseQueue.getInstance().put(msg);
			logger.info("--------PlayerBaseQueue-------Received---"
					+ PlayerBaseQueue.getInstance().getQueueSize());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		String id = ServerCache.getInstance().getId(ctx.channel());
		// 下线存储
		ServerCache.getInstance().logout(ctx.channel(), id);
	}

}
